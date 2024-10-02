package be.kdg.prog6.family.adapter.out;

import be.kdg.prog6.family.domain.MaterialType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "materials")
public class MaterialJpaEntity {
    @Id
    @Column(name = "materialId", columnDefinition = "varchar(36)")
    private UUID materialId;
    
    @Column(name = "materialName")
    private MaterialType name;
    
    @Column(name = "storageCostPerTon")
    private double storageCostPerTon;

    public MaterialJpaEntity(UUID materialId, MaterialType name, double storageCostPerTon) {
        this.materialId = materialId;
        this.name = name;
        this.storageCostPerTon = storageCostPerTon;
    }

    public MaterialJpaEntity() {
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public MaterialType getName() {
        return name;
    }

    public void setName(MaterialType name) {
        this.name = name;
    }

    public double getStorageCostPerTon() {
        return storageCostPerTon;
    }

    public void setStorageCostPerTon(double storageCostPerTon) {
        this.storageCostPerTon = storageCostPerTon;
    }
}
