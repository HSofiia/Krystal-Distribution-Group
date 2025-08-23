package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.PO;

import java.util.List;

public interface LoadPOPort {
    PO getByPurchaseOrderNumber(String purchaseOrderNumber);
    List<PO> getAllPurchaseOrders();
}
