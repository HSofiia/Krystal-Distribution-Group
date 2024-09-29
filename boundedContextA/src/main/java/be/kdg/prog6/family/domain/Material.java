package be.kdg.prog6.family.domain;

import java.util.UUID;

public class Material {
    private UUID materialId;
    private String name; // e.g., Petcoke, Gypsum
    private double storageCostPerTon;

    public Material(UUID materialId, String name, double storageCostPerTon) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStorageCostPerTon() {
        return storageCostPerTon;
    }

    public void setStorageCostPerTon(double storageCostPerTon) {
        this.storageCostPerTon = storageCostPerTon;
    }
}
