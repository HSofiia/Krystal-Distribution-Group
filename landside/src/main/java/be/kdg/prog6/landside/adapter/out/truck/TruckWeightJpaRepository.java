package be.kdg.prog6.landside.adapter.out.truck;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TruckWeightJpaRepository extends JpaRepository<TruckWeightJpaEntity, UUID> {
    Optional<TruckWeightJpaEntity> findByLicencePlate(String licensePlate);
}
