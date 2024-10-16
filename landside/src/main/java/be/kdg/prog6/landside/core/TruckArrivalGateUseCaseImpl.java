package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.TruckArrivalGateUseCase;
import be.kdg.prog6.landside.port.out.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.UpdatedAppointmentPort;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class TruckArrivalGateUseCaseImpl implements TruckArrivalGateUseCase {

    private final LoadAppointmentPort loadAppointmentPort;
    private final List<UpdatedAppointmentPort> updatedAppointment;
    private static final int ALLOWED_TIME_WINDOW_MINUTES = 60;
    private final Logger log = getLogger(TruckArrivalGateUseCaseImpl.class);

    public TruckArrivalGateUseCaseImpl(LoadAppointmentPort loadAppointmentPort, List<UpdatedAppointmentPort> updatedAppointment) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.updatedAppointment = updatedAppointment;
    }

    @Override
    public Optional<TruckArrival> openGate(TruckArrival arrival) {
        log.info("Finding appointment for license plate: {}", arrival.plate().licensePlate());
        Optional<Appointment> appointmentOpt = loadAppointmentPort.findAppointmentByLicencePlate(arrival.plate().licensePlate());

        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            log.info("Found appointment with ID: {}, scheduledTime: {}, status: {}", appointment.getId(), appointment.getScheduledTime(), appointment.getStatus());

            LocalDateTime scheduledTime = appointment.getScheduledTime();
            LocalDateTime truckArrivalTime = arrival.arrivalTime();

            LocalDateTime earliestAllowedTime = scheduledTime;
            LocalDateTime latestAllowedTime = scheduledTime.plusMinutes(ALLOWED_TIME_WINDOW_MINUTES);

            if (truckArrivalTime.isBefore(earliestAllowedTime)) {
                log.info("Truck arrived early at: {}", truckArrivalTime);
                return Optional.empty();

            } else if (truckArrivalTime.isAfter(earliestAllowedTime) && truckArrivalTime.isBefore(latestAllowedTime)) {
                log.info("Truck arrived on time at: {}", truckArrivalTime);
                Activity activity = new Activity(
                        new ActivityId(appointment.getId(), UUID.randomUUID()),
                        ActivityType.ARRIVED,
                        truckArrivalTime,
                        AppointmentStatus.SCHEDULED,
                        appointment.getWarehouseId(),
                        appointment.getTruck()
                );

                updatedAppointment.forEach(port -> port.activityCreated(appointment, activity));
                return Optional.of(arrival);
            } else if (truckArrivalTime.isAfter(latestAllowedTime)) {
                log.info("Truck arrived late at: {}", truckArrivalTime);
                return Optional.empty();

            }
        } else {
            log.info("No appointment found for license plate: {}", arrival.plate().licensePlate());
        }

        return Optional.empty();
    }
}
