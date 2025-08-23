package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.PONumber;

@FunctionalInterface
public interface WarehouseIssueMaterialsUseCase {
    void issueMaterialsAmount(PONumber poNumber);
}
