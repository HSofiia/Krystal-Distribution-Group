package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.WeighingBridgeCommand;
import be.kdg.prog6.landside.port.out.CreatePdtPort;
import be.kdg.prog6.landside.port.out.appointment.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.appointment.SaveAppointmentPort;
import be.kdg.prog6.landside.port.out.appointment.UpdatedAppointmentPort;
import be.kdg.prog6.landside.port.out.truck.LoadTruckWeightPort;
import be.kdg.prog6.landside.port.out.truck.SaveTruckWeightPort;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class LeaveWeighingBridgeUseCaseImpl implements LeaveWeighingBridgeUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final SaveTruckWeightPort saveTruckWeightPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final LoadTruckWeightPort loadTruckWeightPort;
    private final List<UpdatedAppointmentPort> updatedAppointment;
    private final CreatePdtPort createPdtPort;
    private final Logger log = getLogger(LeaveWeighingBridgeUseCaseImpl.class);

    public LeaveWeighingBridgeUseCaseImpl(LoadAppointmentPort loadAppointmentPort, SaveTruckWeightPort saveTruckWeightPort, SaveAppointmentPort saveAppointmentPort, LoadTruckWeightPort loadTruckWeightPort, List<UpdatedAppointmentPort> updatedAppointment, CreatePdtPort createPdtPort) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.saveTruckWeightPort = saveTruckWeightPort;
        this.saveAppointmentPort = saveAppointmentPort;
        this.loadTruckWeightPort = loadTruckWeightPort;
        this.updatedAppointment = updatedAppointment;
        this.createPdtPort = createPdtPort;
    }


    @Override
    public WBT leaveWeighingBridge(WeighingBridgeCommand command) {
        log.info("Processing truck arrival to weighing bridge for license plate: {}", command.licencePlate());

        Optional<Appointment> appointmentOpt = loadAppointmentPort.findAppointmentByLicencePlate(command.licencePlate());

        if (appointmentOpt.isEmpty()) {
            log.warn("No appointment found for license plate: {}", command.licencePlate());
            throw new IllegalArgumentException("No valid appointment found for the truck.");
        }

        Appointment appointment = appointmentOpt.get();

        // Save the truck weight
        TruckWeight leavingTruckWeight = new TruckWeight(
                UUID.randomUUID(),
                new TruckPlate(command.licencePlate()),
                command.weight(),
                command.time(),
                appointment.getWarehouseNumber()
        );

//        if (appointment.getStatus() != AppointmentStatus.ARRIVED_ON_TIME) {
//            log.warn("Appointment for license plate {} is not ARRIVED_ON_TIME", command.licencePlate());
//            throw new IllegalArgumentException("Truck cannot proceed to weighing bridge as it is not marked as ARRIVED_ON_TIME.");
//        }

        Optional<TruckWeight> initialTruckWeight = loadTruckWeightPort.loadTruckWeightByLicensePlate(command.licencePlate());

        double netWeight = initialTruckWeight.get().weight() - leavingTruckWeight.weight();
        log.info("weight: {}", netWeight);

        saveTruckWeightPort.save(leavingTruckWeight);
        log.info("Truck weight saved for license plate: {}", leavingTruckWeight.licencePlate());

        Activity activity = appointment.addActivity(
                ActivityType.OUT_SITE,
                TruckStatus.OUT_SITE,
                initialTruckWeight.get().time()
        );
        appointment.setStatus(AppointmentStatus.COMPLETED);
        saveAppointmentPort.update(appointment);

        updatedAppointment.forEach(port -> port.activityCreated(appointment, activity));

        createPdtPort.sendPdt(new ConveyorPayloadEvent(
                appointment.getWarehouseNumber(),
                leavingTruckWeight.time(),
                netWeight,
                appointment.getMaterialType().name()
        ));

        log.info("Appointment status updated to ARRIVED_ON_TIME for license plate: {}", leavingTruckWeight.licencePlate());

        return new WBT(
                leavingTruckWeight.licencePlate(),
                initialTruckWeight.get().weight(),
                leavingTruckWeight.weight(),
                netWeight,
                initialTruckWeight.get().time(),
                leavingTruckWeight.time()
                );
    }
}
