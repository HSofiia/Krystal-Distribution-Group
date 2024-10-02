package be.kdg.prog6.family.adapter.out.warehouse;

import be.kdg.prog6.family.domain.MaterialType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
public class WarehouseJpaEntity {
    @Id
    @Column(name = "warehouseId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "materialType")
    private MaterialType materialType;

    @Column(name = "capacity")
    private double capacity;

    @Column(name = "is_below_eighty_percent")
    private boolean isBelowEightyPercent;

    @Column(name = "seller")
    private UUID sellerId;

    public WarehouseJpaEntity(UUID warehouseId, int warehouseNumber, MaterialType materialType, double capacity, boolean isBelowEightyPercent, UUID sellerId) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.capacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
        this.sellerId = sellerId;
    }

    public WarehouseJpaEntity(UUID warehouseId, int warehouseNumber, MaterialType materialType, double capacity, boolean isBelowEightyPercent) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.capacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
    }

    public WarehouseJpaEntity() {
    }
}

