package be.kdg.prog6.landside.adapter.in.api.dto;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record WBTDto(TruckPlate licencePlate, double arrivalWeight, double leavingWeight, double netWeight, LocalDateTime arrivalTime, LocalDateTime leavingTime) {
}
