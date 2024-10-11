package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.TruckArrival;

import java.util.Optional;

@FunctionalInterface
public interface TruckOpenGateUseCase {
    Optional<TruckArrival> openGate(TruckArrival arrival);
}
