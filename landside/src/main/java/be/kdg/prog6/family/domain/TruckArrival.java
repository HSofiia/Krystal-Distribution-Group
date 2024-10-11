package be.kdg.prog6.family.domain;

import java.time.LocalDateTime;

public record TruckArrival(TruckPlate plate, LocalDateTime arrivalTime) {
}
