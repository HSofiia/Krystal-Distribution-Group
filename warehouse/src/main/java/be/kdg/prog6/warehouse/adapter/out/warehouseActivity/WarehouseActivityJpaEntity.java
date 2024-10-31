package be.kdg.prog6.warehouse.adapter.out.warehouseActivity;

import be.kdg.prog6.warehouse.adapter.out.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.common.domain.ActivityAmountType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warehouseActivities", catalog = "whside")
public class WarehouseActivityJpaEntity {
    @EmbeddedId
    private WarehouseActivityIdJpaEntity id;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ActivityAmountType type;

    @Column(name = "amount")
    private double amount;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "warehouseNumber", referencedColumnName = "warehouseNumber", insertable = false, updatable = false)
    private WarehouseJpaEntity warehouse;

    public WarehouseActivityJpaEntity() {
    }

    public WarehouseActivityJpaEntity(WarehouseActivityIdJpaEntity id, ActivityAmountType type, double amount, LocalDateTime time, WarehouseJpaEntity warehouse) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.warehouse = warehouse;
    }

    public WarehouseActivityIdJpaEntity getId() {
        return id;
    }

    public void setId(WarehouseActivityIdJpaEntity id) {
        this.id = id;
    }

    public ActivityAmountType getType() {
        return type;
    }

    public void setType(ActivityAmountType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public WarehouseJpaEntity getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseJpaEntity warehouse) {
        this.warehouse = warehouse;
    }
}
