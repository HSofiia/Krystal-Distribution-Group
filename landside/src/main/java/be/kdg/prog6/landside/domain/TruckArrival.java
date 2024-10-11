package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;

public record TruckArrival(TruckPlate plate, LocalDateTime arrivalTime) {
}
