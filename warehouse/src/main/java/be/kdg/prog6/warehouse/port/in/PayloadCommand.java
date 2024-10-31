package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.common.domain.ActivityAmountType;

import java.time.LocalDateTime;

public record PayloadCommand(int warehouseNumber, LocalDateTime time, double netWeight, ActivityAmountType activityType) {
}
