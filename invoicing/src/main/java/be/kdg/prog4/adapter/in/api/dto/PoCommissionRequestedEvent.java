package be.kdg.prog4.adapter.in.api.dto;

import java.util.List;
import java.util.UUID;

public record PoCommissionRequestedEvent(
        List<OrderLinePayload> orderLines, UUID sellerId, String poNumber
) {
    public record OrderLinePayload(String materialType, double quantity, String measure) {}
}
