package be.kdg.prog6.landside.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {
    Optional<WarehouseJpaEntity> findByWarehouseId(UUID warehouseId);

    Optional<WarehouseJpaEntity> findByWarehouseNumber(int warehouseNumber);

    Optional<WarehouseJpaEntity> findByMaterialType(MaterialType materialType);
}
