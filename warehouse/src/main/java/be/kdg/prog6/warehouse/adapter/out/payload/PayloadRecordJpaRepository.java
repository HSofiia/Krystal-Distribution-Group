package be.kdg.prog6.warehouse.adapter.out.payload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PayloadRecordJpaRepository extends JpaRepository<PayloadActivityJpaEntity, UUID> {
    Optional<PayloadActivityJpaEntity> findFirstByWarehouseNumberAndNetWeight(int warehouseNumber, double netWeight);
}
