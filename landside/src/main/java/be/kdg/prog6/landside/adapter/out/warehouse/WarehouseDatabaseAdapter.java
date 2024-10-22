package be.kdg.prog6.landside.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehousePort;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, LoadWarehouseByMaterialTypePort {
    private final WarehouseJpaRepository warehouseJpaRepository;
    private final Logger log = getLogger(WarehouseDatabaseAdapter.class);


    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    @Override
    public Optional<Warehouse> loadWarehouseById(UUID warehouseId) {
        return warehouseJpaRepository.findByWarehouseId(warehouseId)
                .map(this::toWarehouse);
    }

    @Override
    public Warehouse getWarehouse(MaterialType materialType) {
        Optional<WarehouseJpaEntity> warehouseEntity = warehouseJpaRepository.findByMaterialType(materialType);
        log.info("Looking for warehouse with material type: {}", materialType);

        return warehouseEntity.map(this::toWarehouse)
                .orElseThrow(() -> new IllegalArgumentException("No warehouse found for material type: " + materialType));
    }

    private Warehouse toWarehouse(final WarehouseJpaEntity warehouseJpaEntity) {
        final SellerId sellerId = new SellerId(warehouseJpaEntity.getSellerId());
        return new Warehouse(
                warehouseJpaEntity.getWarehouseId(),
                warehouseJpaEntity.getWarehouseNumber(),
                warehouseJpaEntity.getMaterialType(),
                warehouseJpaEntity.isBelowEightyPercent(),
                sellerId,
                warehouseJpaEntity.getCurrentCapacity(),
                warehouseJpaEntity.getMaxCapacity()
                );
    }
}
