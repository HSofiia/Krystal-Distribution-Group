package be.kdg.prog4.event;

import be.kdg.prog4.domain.OrderLine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CommissionEvent(String poNumber, UUID sellerId, List<OrderLine> orderLines) {
}
