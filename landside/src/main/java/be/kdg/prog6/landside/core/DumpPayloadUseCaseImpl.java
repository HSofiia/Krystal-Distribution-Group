package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.port.in.DumpPayloadCommand;
import be.kdg.prog6.landside.port.in.DumpPayloadUseCase;
import be.kdg.prog6.landside.port.out.ConveyorPayloadPort;
import be.kdg.prog6.landside.port.out.appointment.LoadAppointmentPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DumpPayloadUseCaseImpl implements DumpPayloadUseCase {

    private LoadAppointmentPort loadAppointmentPort;
    private ConveyorPayloadPort conveyorPayloadPort;

    public DumpPayloadUseCaseImpl(LoadAppointmentPort loadAppointmentPort, ConveyorPayloadPort conveyorPayloadPort) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.conveyorPayloadPort = conveyorPayloadPort;
    }

    @Override
    public void dumpPayloadOnConveyorBelt(DumpPayloadCommand command) {
        Optional<Appointment> appointmentOpt = loadAppointmentPort.findAppointmentByLicencePlate(command.licencePlate().licensePlate());

        Appointment appointment = appointmentOpt.get();

        conveyorPayloadPort.conveyorPayload(appointment.getMaterialType(), appointment.getWarehouseNumber(), appointment.getTruck(), command.time());
    }
}
