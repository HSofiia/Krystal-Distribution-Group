package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.TruckWeight;
import be.kdg.prog6.landside.port.in.ArriveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.WeighingBridgeCommand;
import be.kdg.prog6.landside.port.out.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.LoadTruckWeightPort;
import be.kdg.prog6.landside.port.out.SaveAppointmentPort;
import be.kdg.prog6.landside.port.out.SaveTruckWeightPort;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class LeaveWeighingBridgeUseCaseImpl implements LeaveWeighingBridgeUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final SaveTruckWeightPort saveTruckWeightPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final LoadTruckWeightPort loadTruckWeightPort;
    private final Logger log = getLogger(LeaveWeighingBridgeUseCaseImpl.class);

    public LeaveWeighingBridgeUseCaseImpl(LoadAppointmentPort loadAppointmentPort, SaveTruckWeightPort saveTruckWeightPort, SaveAppointmentPort saveAppointmentPort, LoadTruckWeightPort loadTruckWeightPort) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.saveTruckWeightPort = saveTruckWeightPort;
        this.saveAppointmentPort = saveAppointmentPort;
        this.loadTruckWeightPort = loadTruckWeightPort;
    }


    @Override
    public TruckWeight leaveWeighingBridge(WeighingBridgeCommand command) {
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
                command.time()
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

        // Update the appointment status to ON_SITE after weighing
        appointment.setStatus(AppointmentStatus.COMPLETED);
        saveAppointmentPort.update(appointment);
        log.info("Appointment status updated to ARRIVED_ON_TIME for license plate: {}", leavingTruckWeight.licencePlate());

        return leavingTruckWeight;
    }
}
