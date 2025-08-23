package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ShipmentOrder;

public interface UpdateSOPort {
    ShipmentOrder updateShipmentOrder(ShipmentOrder shipmentOrder);
}
