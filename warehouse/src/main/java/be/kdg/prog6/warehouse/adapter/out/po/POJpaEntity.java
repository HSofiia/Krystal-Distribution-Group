package be.kdg.prog6.warehouse.adapter.out.po;

import be.kdg.prog6.warehouse.adapter.out.orderLine.OrderLineJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.seller.SellerJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchase_orders", catalog = "warehouse")
public class POJpaEntity {
    @Id
    private String poNumber;

    @Column
    private String orderStatus;

    @Column
    private LocalDateTime receivedDateTime;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderLineJpaEntity> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    private SellerJpaEntity seller;

    public POJpaEntity() {
    }

    public POJpaEntity(String poNumber, String orderStatus, LocalDateTime receivedDateTime) {
        this.poNumber = poNumber;
        this.orderStatus = orderStatus;
        this.receivedDateTime = receivedDateTime;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(LocalDateTime receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public List<OrderLineJpaEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineJpaEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public SellerJpaEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerJpaEntity seller) {
        this.seller = seller;
    }
}
