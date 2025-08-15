package be.kdg.prog6.common.event;

import java.time.LocalDateTime;

public record ConveyorPayloadEvent(int warehouseNumber, LocalDateTime time, Double netWeight, String materialType) {
}