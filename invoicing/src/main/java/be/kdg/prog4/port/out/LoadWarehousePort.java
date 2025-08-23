package be.kdg.prog4.port.out;

import be.kdg.prog4.domain.Warehouse;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.util.List;

public interface LoadWarehousePort {
    Warehouse loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType);
    List<Warehouse> loadWarehousesBySellerId(SellerId sellerId);
}
