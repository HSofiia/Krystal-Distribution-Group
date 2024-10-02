package be.kdg.prog6.family.domain;

import java.util.Objects;

public record Truck (String licensePlate) {
    public Truck {
        Objects.requireNonNull(licensePlate);
    }
}
