package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.DumpPayloadCommand;
import be.kdg.prog6.landside.port.in.DumpPayloadUseCase;
import be.kdg.prog6.landside.port.out.CreatePdtPort;
import be.kdg.prog6.landside.port.out.appointment.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.appointment.UpdatedAppointmentPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class DumpPayloadUseCaseImpl implements DumpPayloadUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final UpdatedAppointmentPort updatedAppointment;
    private final CreatePdtPort createPdtPort;
    private final Logger logger = Logger.getLogger(DumpPayloadUseCaseImpl.class.getName());

    public DumpPayloadUseCaseImpl(LoadAppointmentPort loadAppointmentPort, UpdatedAppointmentPort updatedAppointment, CreatePdtPort createPdtPort) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.updatedAppointment = updatedAppointment;
        this.createPdtPort = createPdtPort;
    }

    @Override
    @Transactional
    public Appointment dumpPayloadOnConveyorBelt(DumpPayloadCommand command) {
        Appointment appointment = loadAppointmentPort.findAppointmentByLicencePlateAndStatus(command.licencePlate().licensePlate(), AppointmentStatus.ON_SITE);

        Activity activity = appointment.addActivity(ActivityType.DUMP_LOAD, TruckStatus.ON_SITE, command.time());
        updatedAppointment.activityCreated(appointment, activity);
        createPdtPort.sendPdt(new ConveyorPayloadEvent(
                appointment.getWarehouseNumber(),
                command.time(),
                0.0,
                appointment.getMaterialType().name()
                ));
                logger.info(String.format("Truck %s dumped material on conveyor belt.", command.licencePlate()));

        return appointment;
    }
}
