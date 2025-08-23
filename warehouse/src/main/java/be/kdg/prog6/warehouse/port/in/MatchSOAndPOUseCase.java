package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.PONumber;

public interface MatchSOAndPOUseCase {
    void matchSOandPO(PONumber poNumber);
}
