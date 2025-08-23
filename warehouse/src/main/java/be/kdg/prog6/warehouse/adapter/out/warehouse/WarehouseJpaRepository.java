package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, Integer> {
    Optional<WarehouseJpaEntity> findByWarehouseNumber(int number);
    Optional<WarehouseJpaEntity> findBySellerIdAndMaterialType(UUID ownerId, MaterialType materialType);
}
