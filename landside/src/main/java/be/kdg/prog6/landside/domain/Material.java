package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.MaterialType;

import java.util.UUID;

public class Material {
    private UUID materialId;
    private MaterialType name;
    private double storageCostPerTon;

    public Material(UUID materialId, MaterialType name, double storageCostPerTon) {
        this.materialId = materialId;
        this.name = name;
        this.storageCostPerTon = storageCostPerTon;
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
