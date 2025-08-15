package be.kdg.prog6.warehouse.port.in;

import java.time.LocalDateTime;

public record PayloadCommand(int warehouseNumber, LocalDateTime time, double netWeight) {
}
