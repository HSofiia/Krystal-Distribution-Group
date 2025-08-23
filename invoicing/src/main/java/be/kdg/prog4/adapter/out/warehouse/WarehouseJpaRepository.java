package be.kdg.prog4.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, UUID> {
    @Query("""
           select w from WarehouseJpaEntity w
           left join fetch w.payloads a
           where w.sellerId = :sellerId and w.materialType = :materialType
           """)
    Optional<WarehouseJpaEntity> findBySellerIdAndMaterialTypeFetched(UUID sellerId, String materialType);

    @Query("""
           select w from WarehouseJpaEntity w
           left join fetch w.payloads a
           where w.sellerId = :sellerId
           """)
    List<WarehouseJpaEntity> findAllBySellerIdFetched(UUID sellerId);
}
