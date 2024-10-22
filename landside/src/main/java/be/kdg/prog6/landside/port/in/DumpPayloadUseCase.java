package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.domain.Appointment;

import java.time.LocalDateTime;

@FunctionalInterface
public interface DumpPayloadUseCase {
    void dumpPayloadOnConveyorBelt(DumpPayloadCommand command);
}
