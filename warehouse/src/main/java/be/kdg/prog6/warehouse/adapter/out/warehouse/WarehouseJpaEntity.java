package be.kdg.prog6.warehouse.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.warehouse.adapter.out.warehouseActivity.WarehouseActivityJpaEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "warehouses", catalog = "whside")
public class WarehouseJpaEntity {
    @Id
    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "capacity")
    private double capacity;

    @Column(name = "maxCapacity")
    private double maxCapacity;

    @Column(name = "materialType")
    @Enumerated(value = EnumType.STRING)
    private MaterialType materialType;

    @Transient
    private LocalDateTime capacityReceivedTime;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<WarehouseActivityJpaEntity> activities;

    public WarehouseJpaEntity(int warehouseNumber, double capacity, double maxCapacity, MaterialType materialType, LocalDateTime capacityReceivedTime, List<WarehouseActivityJpaEntity> activities) {
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

    public List<WarehouseActivityJpaEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<WarehouseActivityJpaEntity> activities) {
        this.activities = activities;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}

