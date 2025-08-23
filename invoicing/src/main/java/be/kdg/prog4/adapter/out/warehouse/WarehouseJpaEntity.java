package be.kdg.prog4.adapter.out.warehouse;

import be.kdg.prog4.adapter.out.payload.PayloadJpaEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "warehouses", catalog = "invoicing")
public class WarehouseJpaEntity {

//    @GeneratedValue(strategy = GenerationType.UUID)
//    @JdbcTypeCode(SqlTypes.VARCHAR)
//    private UUID warehouseId;
    @Id
    @Column(name = "warehouse_number")
    private int warehouseNumber;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sellerId;

    @OneToMany(mappedBy = "warehouse")
    private List<PayloadJpaEntity> payloads;

    public WarehouseJpaEntity() {

    }

//    public UUID getWarehouseId() {
//        return warehouseId;
//    }
//
//    public void setWarehouseId(UUID warehouseId) {
//        this.warehouseId = warehouseId;
//    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public List<PayloadJpaEntity> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<PayloadJpaEntity> payloads) {
        this.payloads = payloads;
    }
}
