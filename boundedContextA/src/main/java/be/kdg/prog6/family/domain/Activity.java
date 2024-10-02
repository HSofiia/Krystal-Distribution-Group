package be.kdg.prog6.family.domain;

import java.time.LocalDateTime;

public record Activity(ActivityId id, LocalDateTime time, AppointmentStatus status, int warehouseNumber) {
}
