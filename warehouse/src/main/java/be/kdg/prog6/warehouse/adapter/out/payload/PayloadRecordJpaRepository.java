package be.kdg.prog6.warehouse.adapter.out.payload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PayloadRecordJpaRepository extends JpaRepository<PayloadActivityJpaEntity, UUID> {
    @Query("select p from PayloadActivityJpaEntity p " +
            "where p.warehouse.warehouseNumber = :warehouseNumber " +
            "and p.netWeight = :netWeight " +
            "and p.time = :time")
    Optional<PayloadActivityJpaEntity> findFirstByWarehouseAndTime(int warehouseNumber, double netWeight, LocalDateTime time);

    List<PayloadActivityJpaEntity> findAllByTimeGreaterThanEqualAndWarehouseWarehouseNumber(LocalDateTime time, int warehouseNumber);
}
