package be.kdg.prog6.landside.port.out.warehouse;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.domain.WarehouseProjector;

@FunctionalInterface
public interface LoadWarehouseByMaterialTypePort {
    WarehouseProjector getWarehouse(MaterialType materialType);

}
