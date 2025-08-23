package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Measure;

public record OrderLine(MaterialType materialType, double quantity, Measure measure) {
    public Double getAmount() {
        return quantity * measure.toTonsFactor();
    }
}