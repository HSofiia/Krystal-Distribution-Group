package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.warehouse.adapter.out.payload.PayloadActivityJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.payload.PayloadRecordJpaRepository;
import be.kdg.prog6.warehouse.adapter.out.seller.SellerJpaEntity;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.port.out.UpdateWarehouseCapacityPort;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, UpdateWarehouseCapacityPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDatabaseAdapter.class);

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final PayloadRecordJpaRepository payloadRecordJpaRepository;

    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository, PayloadRecordJpaRepository payloadRecordJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.payloadRecordJpaRepository = payloadRecordJpaRepository;
    }

    @Override
    public Warehouse loadWarehouseByNumberSnapshot(int warehouseNumber) {
        WarehouseJpaEntity entity = warehouseJpaRepository.findByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Warehouse %s not found".formatted(warehouseNumber)));
        return fromJpa(entity);
    }


    @Override
    public Warehouse loadWarehouseByOwnerIdAndMaterialType(SellerId id, MaterialType materialType){
        return warehouseJpaRepository
                .findBySellerIdAndMaterialType(id.sellerId(), materialType)
                .map(this::fromJpa)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found"));
    }

    @Override
    public List<Warehouse> loadAllWarehousesSnapshot() {
        return warehouseJpaRepository.findAll()
                .stream()
                .map(w -> {
                    List<PayloadActivityJpaEntity> payloadActivities = payloadRecordJpaRepository
                            .findAllByTimeGreaterThanEqualAndWarehouseWarehouseNumber(
                                    w.getCapacityReceivedTime(), w.getWarehouseNumber());
                    w.setActivities(payloadActivities);
                    return fromJpa(w);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateWarehouse(Warehouse warehouse) {
        int number = warehouse.getWarehouseNumber();

        Warehouse fresh = loadWarehouseByNumberSnapshot(number);

        WarehouseCurrentCapacity currentCapacity = fresh.calculateCapacity();

        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository
                .findByWarehouseNumber(number)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse %s not found".formatted(number)));

        warehouseJpaEntity.setCapacity(currentCapacity.number());
        warehouseJpaEntity.setCapacityReceivedTime(currentCapacity.time());

        LOGGER.info("Updating warehouse {} with capacity {} at {}", number, currentCapacity.number(), currentCapacity.time());
        warehouseJpaRepository.save(warehouseJpaEntity);
    }

    public Warehouse fromJpa(WarehouseJpaEntity warehouseJpaEntity) {
        WarehouseCurrentCapacity currentCapacity = new WarehouseCurrentCapacity(
                warehouseJpaEntity.getCapacity(),
                warehouseJpaEntity.getCapacityReceivedTime()
        );

        LocalDateTime snapshot = warehouseJpaEntity.getCapacityReceivedTime();
        if (snapshot == null || snapshot.getYear() < 1970 || snapshot.getYear() > 9999) {
            snapshot = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        }

        List<PayloadActivityJpaEntity> rows =
                payloadRecordJpaRepository.findAllByTimeGreaterThanEqualAndWarehouseWarehouseNumber(
                        snapshot,
                        warehouseJpaEntity.getWarehouseNumber()
                );

        List<PayloadActivity> activities = rows.stream()
                .map(row -> new PayloadActivity(
                        new ActivityId(row.getWarehouse().getWarehouseNumber(), row.getId()),
                        row.getNetWeight(),
                        row.getTime(),
                        row.getActivityType()
                ))
                .collect(Collectors.toList());

        ActivityWindow activityWindow = new ActivityWindow(warehouseJpaEntity.getWarehouseNumber(), activities);

        Seller seller = fromJpaSeller(warehouseJpaEntity.getSeller());

        return new Warehouse(
                warehouseJpaEntity.getWarehouseNumber(),
                warehouseJpaEntity.getMaterialType(),
                currentCapacity,
                activityWindow,
                seller
        );
    }

    public static Seller fromJpaSeller(SellerJpaEntity sellerJpaEntity) {
        if (sellerJpaEntity == null) {
            throw new IllegalStateException("Warehouse has no seller linked");
        }
        return new Seller(new SellerId(sellerJpaEntity.getId()), sellerJpaEntity.getSellerName());
    }
}
