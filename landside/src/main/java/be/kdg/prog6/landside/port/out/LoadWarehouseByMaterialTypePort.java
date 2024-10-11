package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.MaterialType;
import be.kdg.prog6.landside.domain.Warehouse;

@FunctionalInterface
public interface LoadWarehouseByMaterialTypePort {
    Warehouse getWarehouse(MaterialType materialType);

}
