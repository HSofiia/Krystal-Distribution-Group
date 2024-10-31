package be.kdg.prog6.warehouse.adapter.out.warehouseActivity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WarehouseActivityJpaRepository extends JpaRepository<WarehouseActivityJpaEntity, WarehouseActivityIdJpaEntity> {
    List<WarehouseActivityJpaEntity> findAllById_WarehouseNumberAndTimeAfter(int warehouseNumber, LocalDateTime time);
}
