package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record WBT(TruckPlate licencePlate, double arrivalWeight,double leavingWeight,double netWeight, LocalDateTime arrivalTime,LocalDateTime leavingTime) {
}
