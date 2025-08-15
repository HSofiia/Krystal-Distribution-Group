package be.kdg.prog6.warehouse.port.in;


@FunctionalInterface
public interface PayloadManagementUseCase {
    void savePayload(PayloadCommand payloadCommand);
}
