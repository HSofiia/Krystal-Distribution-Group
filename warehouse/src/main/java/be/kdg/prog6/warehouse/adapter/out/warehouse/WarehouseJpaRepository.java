package be.kdg.prog6.warehouse.adapter.out.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, Integer> {
    Optional<WarehouseJpaEntity> findByWarehouseNumber(int number);
}
