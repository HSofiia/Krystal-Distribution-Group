package be.kdg.prog6.warehouse.adapter.out.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {
    Optional<WarehouseJpaEntity> findByWarehouseNumber(int number);
}
