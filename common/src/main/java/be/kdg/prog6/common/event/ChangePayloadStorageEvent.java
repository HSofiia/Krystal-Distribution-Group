package be.kdg.prog6.common.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangePayloadStorageEvent(UUID payloadId, UUID sellerId, String materialType, double tons, LocalDateTime deliveredAt) {
}
