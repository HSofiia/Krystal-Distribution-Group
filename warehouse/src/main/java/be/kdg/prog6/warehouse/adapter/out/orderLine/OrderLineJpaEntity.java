package be.kdg.prog6.warehouse.adapter.out.orderLine;

import be.kdg.prog6.warehouse.adapter.out.po.POJpaEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_lines", catalog = "warehouse")
public class OrderLineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String materialType;

    @Column
    private String uom;

    @Column
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private POJpaEntity purchaseOrder;

    public OrderLineJpaEntity( Double amount, String uom, String materialType) {
        this.amount = amount;
        this.uom = uom;
        this.materialType = materialType;
    }

    public OrderLineJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public POJpaEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(POJpaEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
