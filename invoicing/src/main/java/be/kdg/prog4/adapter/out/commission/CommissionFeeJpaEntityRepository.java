package be.kdg.prog4.adapter.out.commission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommissionFeeJpaEntityRepository extends JpaRepository<CommissionJpaEntity, UUID> {
    Optional<CommissionJpaEntity> findByPoNumber(String poNumber);
}
