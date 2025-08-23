package be.kdg.prog4.port.in;

import be.kdg.prog6.common.event.ChangePayloadStorageEvent;

@FunctionalInterface
public interface ReceivePayloadUseCase {
    void receive(ChangePayloadStorageEvent changePayloadStorageEvent);
}
