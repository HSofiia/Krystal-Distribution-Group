package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.ArriveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.WeighingBridgeCommand;
import be.kdg.prog6.landside.port.out.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ArriveWeighingBridgeUseCaseImpl implements ArriveWeighingBridgeUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final SaveTruckWeightPort saveTruckWeightPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final Logger log = getLogger(ArriveWeighingBridgeUseCaseImpl.class);

    public ArriveWeighingBridgeUseCaseImpl(LoadAppointmentPort loadAppointmentPort, SaveTruckWeightPort saveTruckWeightPort, SaveAppointmentPort saveAppointmentPort) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.saveTruckWeightPort = saveTruckWeightPort;
        this.saveAppointmentPort = saveAppointmentPort;
    }


    @Override
    public TruckWeight arriveToWeighingBridge(WeighingBridgeCommand command) {
        log.info("Processing truck arrival to weighing bridge for license plate: {}", command.licencePlate());

        Optional<Appointment> appointmentOpt = loadAppointmentPort.findAppointmentByLicencePlate(command.licencePlate());

        if (appointmentOpt.isEmpty()) {
            log.warn("No appointment found for license plate: {}", command.licencePlate());
            throw new IllegalArgumentException("No valid appointment found for the truck.");
        }

        Appointment appointment = appointmentOpt.get();

        if (appointment.getStatus() != AppointmentStatus.ARRIVED_ON_TIME) {
            log.warn("Appointment for license plate {} is not ARRIVED_ON_TIME", command.licencePlate());
            throw new IllegalArgumentException("Truck cannot proceed to weighing bridge as it is not marked as ARRIVED_ON_TIME.");
        }

        // Save the truck weight
        TruckWeight truckWeight = new TruckWeight(
                UUID.randomUUID(),
                new TruckPlate(command.licencePlate()),
                command.weight(),
                command.time()
        );
        saveTruckWeightPort.save(truckWeight);
        log.info("Truck weight saved for license plate: {}", command.licencePlate());

        // Update the appointment status to ON_SITE after weighing
        appointment.setStatus(AppointmentStatus.ARRIVED_ON_TIME);
        saveAppointmentPort.update(appointment);
        log.info("Appointment status updated to ARRIVED_ON_TIME for license plate: {}", command.licencePlate());

        return truckWeight;
    }
}
