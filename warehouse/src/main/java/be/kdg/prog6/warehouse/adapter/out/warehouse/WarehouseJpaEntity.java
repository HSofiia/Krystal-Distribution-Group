package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.warehouse.adapter.out.payload.PayloadActivityJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "warehouses", catalog = "whside")
public class WarehouseJpaEntity {
    @Id
    @Column(name = "warehouse_number")
    private int warehouseNumber;

    @Column(name = "capacity")
    private double capacity;

    @Column(name = "max_capacity")
    private double maxCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type")
    private MaterialType materialType;

    @Column(name = "capacity_received_time")
    private LocalDateTime capacityReceivedTime;

    @OneToMany(mappedBy = "warehouse")
    private List<PayloadActivityJpaEntity> activities;

    public WarehouseJpaEntity(int warehouseNumber, double capacity, double maxCapacity, MaterialType materialType, LocalDateTime capacityReceivedTime, List<PayloadActivityJpaEntity> activities) {
        this.warehouseNumber = warehouseNumber;
        this.capacity = capacity;
        this.maxCapacity = maxCapacity;
        this.materialType = materialType;
        this.capacityReceivedTime = capacityReceivedTime;
        this.activities = activities;
    }

    public WarehouseJpaEntity() {
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public LocalDateTime getCapacityReceivedTime() {
        return capacityReceivedTime;
    }

    public void setCapacityReceivedTime(LocalDateTime capacityReceivedTime) {
        this.capacityReceivedTime = capacityReceivedTime;
    }

    public List<PayloadActivityJpaEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<PayloadActivityJpaEntity> activities) {
        this.activities = activities;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}

