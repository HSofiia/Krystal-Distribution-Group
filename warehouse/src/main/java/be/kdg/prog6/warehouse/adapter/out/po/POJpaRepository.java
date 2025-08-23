package be.kdg.prog6.warehouse.adapter.out.po;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface POJpaRepository extends JpaRepository<POJpaEntity, String> {

    @Query("select p from POJpaEntity p " +
    "left join fetch p.orderLines " +
    "left join fetch p.seller " +
    "where p.poNumber = :number")
    Optional<POJpaEntity> findByPurchaseOrderNumberFetched(String number);


    @Query("select p from POJpaEntity p " +
            "left join fetch p.seller ")
    List<POJpaEntity> findAllWithSellerFetched();
}
