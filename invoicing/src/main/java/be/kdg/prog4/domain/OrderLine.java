package be.kdg.prog4.domain;

import be.kdg.prog6.common.domain.Measure;

public record OrderLine(
        String materialType,
        double quantity,
        Measure unit              // e.g. KT
) {
    public double toTons() {
        return quantity * unit.toTonsFactor();
    }
}
