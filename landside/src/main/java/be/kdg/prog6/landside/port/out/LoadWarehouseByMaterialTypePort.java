package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.domain.Warehouse;

@FunctionalInterface
public interface LoadWarehouseByMaterialTypePort {
    Warehouse getWarehouse(MaterialType materialType);

}
