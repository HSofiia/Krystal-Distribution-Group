package be.kdg.prog6.family.adapter.out;

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
        return warehouseJpaRepository.findByWarehouseId(warehouseId).map(this:toWarehouse);
    }


    private Warehouse toWarehouse(final WarehouseJpaEntity warehouseJpaEntity) {
        return new Warehouse(
                warehouseJpaEntity.getWarehouseId(),
                warehouseJpaEntity.getCapacity(),
                warehouseJpaEntity.isBelowEightyPercent()
        );
    }
}
