package be.kdg.prog6.family.adapter.out.warehouse;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {
    Optional<WarehouseJpaEntity> findByWarehouseId(UUID warehouseId);

    Optional<WarehouseJpaEntity> findByMaterialType(MaterialType materialType);
}
