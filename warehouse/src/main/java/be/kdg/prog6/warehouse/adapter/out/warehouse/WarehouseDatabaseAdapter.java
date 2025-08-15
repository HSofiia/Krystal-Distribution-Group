package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.warehouse.adapter.out.payload.PayloadActivityJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.payload.PayloadRecordJpaRepository;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.port.out.UpdateWarehouseCapacityPort;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumber(warehouseNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Warehouse %s not found".formatted(warehouseNumber)));

        WarehouseCurrentCapacity currentCapacity = toCurrentCapacity(warehouseJpaEntity);

        LocalDateTime snapshot = warehouseJpaEntity.getCapacityReceivedTime();
        if (snapshot == null || snapshot.getYear() < 1970 || snapshot.getYear() > 9999) {
            snapshot = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        }

        List<PayloadActivityJpaEntity> activityRows =
                payloadRecordJpaRepository.findAllByTimeGreaterThanEqualAndWarehouseWarehouseNumber(
                        snapshot,
                        warehouseJpaEntity.getWarehouseNumber()
                );

        List<PayloadActivity> activities = activityRows.stream()
                .map(WarehouseDatabaseAdapter::toDomainActivity)
                .collect(Collectors.toList());

        ActivityWindow activityWindow = new ActivityWindow(warehouseJpaEntity.getWarehouseNumber(), activities);

        return new Warehouse(
                warehouseJpaEntity.getWarehouseNumber(),
                warehouseJpaEntity.getMaterialType(),
                currentCapacity,
                activityWindow
        );
    }

    private static PayloadActivity toDomainActivity(PayloadActivityJpaEntity row) {
        ActivityId id = new ActivityId(row.getWarehouse().getWarehouseNumber(), row.getId());
        return new PayloadActivity(
                id,
                row.getNetWeight(),
                row.getTime(),
                row.getActivityType()
        );
    }

    private static WarehouseCurrentCapacity toCurrentCapacity(final WarehouseJpaEntity warehouseJpaEntity) {
        return new WarehouseCurrentCapacity(warehouseJpaEntity.getCapacity(), warehouseJpaEntity.getCapacityReceivedTime());
    }

    @Override
    @Transactional
    public void updateWarehouse(Warehouse warehouse) {
        int number = warehouse.getWarehouseNumber();

        // Reload fresh state (includes the updated net_weight)
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
}
