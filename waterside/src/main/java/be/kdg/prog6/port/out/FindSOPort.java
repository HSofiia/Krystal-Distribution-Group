package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.OperationStatus;
import be.kdg.prog6.domain.ShipmentOrder;

import java.time.LocalDate;
import java.util.List;

public interface FindSOPort {
    ShipmentOrder getByVesselNumberAndNotStatus(String vesselNumber, OperationStatus statusNot);
    List<ShipmentOrder> findAllShipmentOrderByBunkeringOperationDate(LocalDate date);
}
