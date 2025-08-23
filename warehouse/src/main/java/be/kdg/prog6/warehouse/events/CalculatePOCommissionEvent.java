package be.kdg.prog6.warehouse.events;

import java.util.List;
import java.util.UUID;

public record CalculatePOCommissionEvent(List<CommissionOrderLine> orderLines, UUID sellerId, String poNumber) {
    public record CommissionOrderLine(String materialType, Double quantity, String measure){

    }
}
