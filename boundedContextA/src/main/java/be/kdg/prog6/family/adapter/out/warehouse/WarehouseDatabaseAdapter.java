package be.kdg.prog6.family.adapter.out.warehouse;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.Warehouse;
import be.kdg.prog6.family.port.out.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.family.port.out.LoadWarehousePort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, LoadWarehouseByMaterialTypePort {
    private final WarehouseJpaRepository warehouseJpaRepository;

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
