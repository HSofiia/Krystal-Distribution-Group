package be.kdg.prog6.warehouse.port.in;

import java.time.LocalDateTime;

@FunctionalInterface
public interface PayloadManagementUseCase {
    void savePayload(int warehouseNumber, LocalDateTime time, double netWeight);
}
