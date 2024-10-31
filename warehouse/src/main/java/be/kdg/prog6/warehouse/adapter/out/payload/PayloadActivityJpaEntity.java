package be.kdg.prog6.warehouse.adapter.out.payload;

import be.kdg.prog6.common.domain.ActivityAmountType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pdtActivity", catalog = "whside")
public class PayloadActivityJpaEntity {

    @Id
    @Column
    private UUID id;

    @Column
    private int warehouseNumber;

    @Column
    private double netWeight;

    @Column
    private LocalDateTime time;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ActivityAmountType activityType;

    public PayloadActivityJpaEntity(UUID id, int warehouseNumber, double netWeight, LocalDateTime time, ActivityAmountType activityType) {
        this.id = id;
        this.warehouseNumber = warehouseNumber;
        this.netWeight = netWeight;
        this.time = time;
        this.activityType = activityType;
    }


    public PayloadActivityJpaEntity() {

    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
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

    public ActivityAmountType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityAmountType activityType) {
        this.activityType = activityType;
    }
}
