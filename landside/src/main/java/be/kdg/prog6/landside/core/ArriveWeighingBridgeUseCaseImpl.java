package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.ArriveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.WeighingBridgeCommand;
import be.kdg.prog6.landside.port.out.appointment.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.appointment.SaveAppointmentPort;
import be.kdg.prog6.landside.port.out.appointment.UpdatedAppointmentPort;
import be.kdg.prog6.landside.port.out.truck.SaveTruckWeightPort;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ArriveWeighingBridgeUseCaseImpl implements ArriveWeighingBridgeUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final SaveTruckWeightPort saveTruckWeightPort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final List<UpdatedAppointmentPort> updatedAppointmentPorts;
    private final Logger log = getLogger(ArriveWeighingBridgeUseCaseImpl.class);

    public ArriveWeighingBridgeUseCaseImpl(LoadAppointmentPort loadAppointmentPort, SaveTruckWeightPort saveTruckWeightPort, SaveAppointmentPort saveAppointmentPort, List<UpdatedAppointmentPort> updatedAppointmentPorts) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.saveTruckWeightPort = saveTruckWeightPort;
        this.saveAppointmentPort = saveAppointmentPort;
        this.updatedAppointmentPorts = updatedAppointmentPorts;
    }


    @Override
    public TruckWeight arriveToWeighingBridge(WeighingBridgeCommand command) {
        log.info("Processing truck arrival to weighing bridge for license plate: {}", command.licencePlate());

        Appointment appointment = loadAppointmentPort.findAppointmentByLicencePlate(command.licencePlate())
                .orElseThrow(() -> {
                    log.warn("No appointment found for license plate: {}", command.licencePlate());
                    return new IllegalArgumentException("No valid appointment found for the truck.");
                });

        if (appointment.getStatus() != AppointmentStatus.ARRIVED_ON_TIME) {
            log.warn("Appointment for license plate {} is not ARRIVED_ON_TIME", command.licencePlate());
            throw new IllegalArgumentException("Truck cannot proceed to weighing bridge as it is not marked as ARRIVED_ON_TIME.");
        }

        TruckWeight truckWeight = new TruckWeight(
                UUID.randomUUID(),
                new TruckPlate(command.licencePlate()),
                command.weight(),
                command.time(),
                appointment.getWarehouseNumber()
        );
        saveTruckWeightPort.save(truckWeight);
        log.info("Truck weight saved for license plate: {}", command.licencePlate());

        appointment.setStatus(AppointmentStatus.ON_SITE);
        saveAppointmentPort.update(appointment);

        Activity activity = new Activity(
                new ActivityId(appointment.getId(), UUID.randomUUID()),
                ActivityType.PASS_BRIDGE,
                truckWeight.time(),
                TruckStatus.ON_SITE,
                appointment.getWarehouseId(),
                truckWeight.licencePlate()
        );

        updatedAppointmentPorts.forEach(port -> port.activityCreated(appointment, activity));
        log.info("Appointment status updated to ON_SITE for license plate: {}", command.licencePlate());

        return truckWeight;
    }
}
