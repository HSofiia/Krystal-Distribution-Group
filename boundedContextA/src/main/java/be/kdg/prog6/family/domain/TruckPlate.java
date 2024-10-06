package be.kdg.prog6.family.domain;

import java.util.Objects;

public record TruckPlate(String licensePlate) {
    public TruckPlate {
        Objects.requireNonNull(licensePlate);
    }
}
