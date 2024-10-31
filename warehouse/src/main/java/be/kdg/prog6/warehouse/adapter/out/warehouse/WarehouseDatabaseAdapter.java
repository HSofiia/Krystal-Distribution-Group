package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.warehouse.adapter.out.warehouseActivity.WarehouseActivityIdJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.warehouseActivity.WarehouseActivityJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.warehouseActivity.WarehouseActivityJpaRepository;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.port.out.UpdateWarehouseCapacityPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, UpdateWarehouseCapacityPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDatabaseAdapter.class);

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseActivityJpaRepository warehouseActivityJpaRepository;

    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository, WarehouseActivityJpaRepository warehouseActivityJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseActivityJpaRepository = warehouseActivityJpaRepository;
    }

    @Override
    public Optional<Warehouse> loadWarehouseByNumber(int warehouseNumber) {
        return warehouseJpaRepository.findByWarehouseNumber(warehouseNumber).map(this::toDomain);
    }

    private Warehouse toDomain(final WarehouseJpaEntity warehouse) {
        final int number = warehouse.getWarehouseNumber();
        final ActivityWindow activities = toActivityWindow(warehouse, number);
        final WarehouseCurrentCapacity currentCapacity = toCurrentCapacity(warehouse);
        return new Warehouse(number, currentCapacity, activities);
    }

    private ActivityWindow toActivityWindow(final WarehouseJpaEntity warehouseJpaEntity, int warehouseNumber) {
        final List<PayloadActivity> activities = warehouseActivityJpaRepository
                .findAllById_WarehouseNumberAndTimeAfter(warehouseNumber, warehouseJpaEntity.getCapacityReceivedTime())
                .stream()
                .map(WarehouseDatabaseAdapter::toActivity)
                .collect(Collectors.toList());
        return new ActivityWindow(warehouseNumber, activities);
    }

    private static PayloadActivity toActivity(final WarehouseActivityJpaEntity activity) {
        return new PayloadActivity(
                new ActivityId(activity.getId().getWarehouseNumber(), activity.getId().getActivityId()),
                activity.getAmount()
,                activity.getTime(),
                activity.getType()
        );
    }

    private static WarehouseCurrentCapacity toCurrentCapacity(final WarehouseJpaEntity warehouseJpaEntity) {
        return new WarehouseCurrentCapacity(warehouseJpaEntity.getCapacity(), warehouseJpaEntity.getCapacityReceivedTime());
    }

    @Override
    public void activityAdded(final Warehouse warehouse, final PayloadActivity activity) {
        final int number = warehouse.getWarehouseNumber();

        final WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumber(number).orElseThrow();
        final WarehouseCurrentCapacity currentCapacity = warehouse.calculateCapacity();
        warehouseJpaEntity.setCapacity(currentCapacity.number());
        warehouseJpaEntity.setCapacityReceivedTime(currentCapacity.time());
        warehouseJpaEntity.getActivities().add(toWarehouseActivity(warehouseJpaEntity, activity));
        LOGGER.info("Updating warehouse {} with capacity of {}", number, currentCapacity);
        warehouseJpaRepository.save(warehouseJpaEntity);
    }


    private WarehouseActivityJpaEntity toWarehouseActivity(final WarehouseJpaEntity warehouseJpaEntity,
                                                           final PayloadActivity activity) {
        final WarehouseActivityJpaEntity warehouseActivityJpaEntity = new WarehouseActivityJpaEntity();
        warehouseActivityJpaEntity.setId(WarehouseActivityIdJpaEntity.of(activity.activityId()));
        warehouseActivityJpaEntity.setType(activity.activityType());
        warehouseActivityJpaEntity.setAmount(activity.amount());
        warehouseActivityJpaEntity.setTime(activity.time());
        warehouseActivityJpaEntity.setWarehouse(warehouseJpaEntity);
        return warehouseActivityJpaEntity;
    }
}
