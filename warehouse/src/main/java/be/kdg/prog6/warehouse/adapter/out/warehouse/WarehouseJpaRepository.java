package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, Integer> {
    @Query("""
           select w
           from WarehouseJpaEntity w
           join fetch w.seller
           where w.warehouseNumber = :warehouseNumber
           """)
    Optional<WarehouseJpaEntity> findByWarehouseNumberWithSeller(int warehouseNumber);

    @Query("""
           select w
           from WarehouseJpaEntity w
           join fetch w.seller
           where w.seller.id = :sellerId and w.materialType = :materialType
           """)
    Optional<WarehouseJpaEntity> findBySellerIdAndMaterialTypeWithSeller(UUID sellerId, MaterialType materialType);

    @Query("""
         select w from WarehouseJpaEntity w
         join fetch w.seller
         """)
    List<WarehouseJpaEntity> findAllWithSeller();
}
