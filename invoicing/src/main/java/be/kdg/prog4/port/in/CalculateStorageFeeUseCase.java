package be.kdg.prog4.port.in;

import be.kdg.prog6.common.domain.SellerId;

import java.time.LocalDate;

public interface CalculateStorageFeeUseCase {
    double calculate(SellerId sellerId, LocalDate asOfDate);
}
