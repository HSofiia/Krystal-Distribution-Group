package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.TruckArrival;

import java.util.Optional;

@FunctionalInterface
public interface TruckOpenGateUseCase {
    Optional<TruckArrival> openGate(TruckArrival arrival);
}
