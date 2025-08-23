package be.kdg.prog6.adapter.out.shipmentOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShipmentOrderJpaEntityRepository extends JpaRepository<ShipmentOrderJpaEntity, String> {

    @Query("select s from ShipmentOrderJpaEntity  s " +
    "where s.vesselNumber = :vesselNumber " +
    "and s.shipmentStatus != :shipmentStatus ")
    Optional<ShipmentOrderJpaEntity> findByVesselNumberAndNotStatusFetched(String vesselNumber, String shipmentStatus);

    List<ShipmentOrderJpaEntity> findAllByBunkeringOperationDate(LocalDate bunkeringOperationDate);
}
