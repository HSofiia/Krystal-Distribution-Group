package be.kdg.prog6.family.adapter.out.warehouse;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.Warehouse;
import be.kdg.prog6.family.port.out.LoadWarehousePort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort {
    private final WarehouseJpaRepository warehouseJpaRepository;

    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    @Override
    public Optional<Warehouse> loadWarehouseById(UUID warehouseId) {
        return warehouseJpaRepository.findByWarehouseId(warehouseId).map(this::toWarehouse);
    }

    @Override
    public Warehouse getWarehouse(SellerId sellerId, MaterialType materialType) {
        return null;
    }

    private Warehouse toWarehouse(final WarehouseJpaEntity warehouseJpaEntity) {
        final SellerId sellerId = new SellerId(warehouseJpaEntity.getSellerId());
        return new Warehouse(
                warehouseJpaEntity.getWarehouseId(),
                warehouseJpaEntity.getWarehouseNumber(),
                warehouseJpaEntity.getMaterialType(),
                warehouseJpaEntity.getCapacity(),
                warehouseJpaEntity.isBelowEightyPercent(),
                sellerId
        );
    }
}
