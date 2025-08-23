package be.kdg.prog6.warehouse.domain;

import java.time.LocalDateTime;

public record WarehouseCurrentCapacity(double capacity, LocalDateTime time) {
}
