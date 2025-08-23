package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.PO;

public interface UpdatePOPort {
    void update(PO purchaseOrder);
}
