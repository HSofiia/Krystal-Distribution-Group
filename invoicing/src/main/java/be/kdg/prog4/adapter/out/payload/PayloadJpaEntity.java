package be.kdg.prog4.adapter.out.payload;

import be.kdg.prog4.adapter.out.warehouse.WarehouseJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payloads", catalog = "invoicing")
public class PayloadJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID payloadId;

    @Column(nullable = false)
    private double netTons;

    @Column(nullable = false)
    private LocalDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_number")
    private WarehouseJpaEntity warehouse;

    public PayloadJpaEntity() {

    }

    public PayloadJpaEntity(UUID payloadId, double netTons, LocalDateTime deliveredAt, WarehouseJpaEntity warehouse) {
        this.payloadId = payloadId;
        this.netTons = netTons;
        this.deliveredAt = deliveredAt;
        this.warehouse = warehouse;
    }

    public UUID getPayloadId() {
        return payloadId;
    }

    public void setPayloadId(UUID payloadId) {
        this.payloadId = payloadId;
    }

    public double getNetTons() {
        return netTons;
    }

    public void setNetTons(double netTons) {
        this.netTons = netTons;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public WarehouseJpaEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseJpaEntity warehouse) {
        this.warehouse = warehouse;
    }
}
