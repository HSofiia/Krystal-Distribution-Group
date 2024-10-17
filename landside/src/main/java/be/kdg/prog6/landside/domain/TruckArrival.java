package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record TruckArrival(TruckPlate plate, LocalDateTime arrivalTime, int weighingBridgeNumber) {
}
