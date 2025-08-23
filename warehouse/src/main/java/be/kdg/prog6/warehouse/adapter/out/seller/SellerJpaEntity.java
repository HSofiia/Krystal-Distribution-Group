package be.kdg.prog6.warehouse.adapter.out.seller;

import be.kdg.prog6.warehouse.adapter.out.po.POJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.warehouse.WarehouseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sellers", catalog = "whside")
public class SellerJpaEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String sellerName;

    @OneToMany(mappedBy = "seller")
    private List<POJpaEntity> purchaseOrders;

    public SellerJpaEntity() {

    }

    public SellerJpaEntity(String sellerName, UUID id) {
        this.sellerName = sellerName;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<POJpaEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<POJpaEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
