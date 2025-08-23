package be.kdg.prog4.adapter.out.payload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayloadJpaRepository extends JpaRepository<PayloadJpaEntity, UUID> {
    @Modifying
    @Query("delete from PayloadJpaEntity p where p.warehouse.warehouseNumber = :warehouseNumber")
    void deleteByWarehouseNumber(int warehouseNumber);
}
