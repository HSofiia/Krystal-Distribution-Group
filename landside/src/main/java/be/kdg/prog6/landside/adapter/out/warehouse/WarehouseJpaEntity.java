package be.kdg.prog6.landside.adapter.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "warehouses", catalog = "landside")
public class WarehouseJpaEntity {
    @Id
    @Column(name = "warehouseId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "materialType")
    @Enumerated(value = EnumType.STRING)
    private MaterialType materialType;

    @Column(name = "currentCapacity")
    private double currentCapacity;

    @Column(name = "maxCapacity")
    private double maxCapacity;

    @Column(name = "is_below_eighty_percent")
    private boolean isBelowEightyPercent;

    @Column(name = "seller", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sellerId;

    public WarehouseJpaEntity(UUID warehouseId, int warehouseNumber, MaterialType materialType, double capacity, boolean isBelowEightyPercent, UUID sellerId) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.currentCapacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
        this.sellerId = sellerId;
    }

    public WarehouseJpaEntity(UUID warehouseId, int warehouseNumber, MaterialType materialType, double currentCapacity, double maxCapacity, boolean isBelowEightyPercent, UUID sellerId) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
        this.sellerId = sellerId;
    }

    public WarehouseJpaEntity(UUID warehouseId, int warehouseNumber, MaterialType materialType, double capacity, boolean isBelowEightyPercent) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.currentCapacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
    }

    public WarehouseJpaEntity() {
    }

    public WarehouseJpaEntity(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public boolean isBelowEightyPercent() {
        return isBelowEightyPercent;
    }

    public void setBelowEightyPercent(boolean belowEightyPercent) {
        isBelowEightyPercent = belowEightyPercent;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}

