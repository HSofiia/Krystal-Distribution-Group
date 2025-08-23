package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.events.CalculatePOCommissionEvent;

public interface InvoicingPOPort {
    void publishCommissionRequest(CalculatePOCommissionEvent event);
}
