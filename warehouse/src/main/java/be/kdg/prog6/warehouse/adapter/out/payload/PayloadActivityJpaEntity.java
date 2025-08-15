package be.kdg.prog6.warehouse.adapter.out.payload;

import be.kdg.prog6.common.domain.ActivityType;
import be.kdg.prog6.warehouse.adapter.out.warehouse.WarehouseJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pdt_activity", catalog = "whside")
public class PayloadActivityJpaEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "net_weight")
    private double netWeight;

    @Column(name = "time")
    private LocalDateTime time;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_warehouse_number", nullable = false)
    private WarehouseJpaEntity warehouse;

    public PayloadActivityJpaEntity(UUID id, double netWeight, LocalDateTime time, ActivityType activityType) {
        this.id = id;
        this.netWeight = netWeight;
        this.time = time;
        this.activityType = activityType;
    }

    public PayloadActivityJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WarehouseJpaEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseJpaEntity warehouse) {
        this.warehouse = warehouse;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
