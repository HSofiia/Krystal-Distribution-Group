package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.Warehouse;

@FunctionalInterface
public interface LoadWarehouseByMaterialTypePort {
    Warehouse getWarehouse(MaterialType materialType);

}
