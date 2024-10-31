package be.kdg.prog6.landside.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.landside.domain.WarehouseProjector;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehousePort;
import be.kdg.prog6.landside.port.out.warehouse.UpdatedWarehousePort;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, LoadWarehouseByMaterialTypePort, UpdatedWarehousePort {
    private final WarehouseJpaRepository warehouseJpaRepository;
    private final Logger log = getLogger(WarehouseDatabaseAdapter.class);


    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    @Override
    public Optional<WarehouseProjector> loadWarehouseByNumber(int warehouseId) {
        return warehouseJpaRepository.findByWarehouseNumber(warehouseId)
                .map(this::toWarehouse);
    }

    @Override
    public WarehouseProjector getWarehouse(MaterialType materialType) {
        Optional<WarehouseJpaEntity> warehouseEntity = warehouseJpaRepository.findByMaterialType(materialType);
        log.info("Looking for warehouse with material type: {}", materialType);

        return warehouseEntity.map(this::toWarehouse)
                .orElseThrow(() -> new IllegalArgumentException("No warehouse found for material type: " + materialType));
    }

    private WarehouseProjector toWarehouse(final WarehouseJpaEntity warehouseJpaEntity) {
        final SellerId sellerId = new SellerId(warehouseJpaEntity.getSellerId());
        return new WarehouseProjector(
                warehouseJpaEntity.getWarehouseId(),
                warehouseJpaEntity.getWarehouseNumber(),
                warehouseJpaEntity.getMaterialType(),
                warehouseJpaEntity.isBelowEightyPercent(),
                sellerId,
                warehouseJpaEntity.getCurrentCapacity(),
                warehouseJpaEntity.getMaxCapacity()
                );
    }

    @Override
    public void updateWarehouse(WarehouseProjector warehouse) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumber(warehouse.getWarehouseNumber()).orElseThrow();
        warehouseJpaEntity.setCurrentCapacity(warehouse.getReceivedAmount());
        warehouseJpaEntity.setBelowEightyPercent(warehouse.isEnoughSpace());
        warehouseJpaRepository.save(warehouseJpaEntity);
    }
}
