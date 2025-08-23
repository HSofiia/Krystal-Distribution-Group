package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.List;


public interface LoadWarehousePort {
    Warehouse loadWarehouseByNumberSnapshot(int warehouseNumber);
    Warehouse loadWarehouseByOwnerIdAndMaterialType(SellerId id, MaterialType materialType);
    List<Warehouse> loadAllWarehousesSnapshot();
}
