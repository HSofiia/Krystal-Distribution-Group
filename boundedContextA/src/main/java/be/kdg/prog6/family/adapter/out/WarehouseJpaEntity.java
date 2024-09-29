package be.kdg.prog6.family.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "warehouses")
public class WarehouseJpaEntity {
    @Id
    @Column(name = "warehouseId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "capacity")
    private double capacity;

    @Column(name = "is_below_eighty_percent")
    private boolean isBelowEightyPercent;

    public WarehouseJpaEntity(UUID warehouseId, double capacity, boolean isBelowEightyPercent) {
        this.warehouseId = warehouseId;
        this.capacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
    }

    public WarehouseJpaEntity() {
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean isBelowEightyPercent() {
        return isBelowEightyPercent;
    }

    public void setBelowEightyPercent(boolean belowEightyPercent) {
        isBelowEightyPercent = belowEightyPercent;
    }
}

