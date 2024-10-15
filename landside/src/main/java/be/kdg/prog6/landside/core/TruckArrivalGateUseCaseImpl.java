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

            // Calculate the earliest and latest allowed times
            LocalDateTime earliestAllowedTime = scheduledTime.minusMinutes(ALLOWED_TIME_WINDOW_MINUTES);  // Adjust for early arrivals
            LocalDateTime latestAllowedTime = scheduledTime.plusMinutes(ALLOWED_TIME_WINDOW_MINUTES);     // Adjust for late arrivals

            if (truckArrivalTime.isBefore(earliestAllowedTime)) {
                // If the truck arrives early
                log.info("Truck arrived early at: {}", truckArrivalTime);
                return Optional.empty();  // Optionally, you could also return `Optional.of(arrival)` if you want to allow early arrivals
            } else if (truckArrivalTime.isAfter(earliestAllowedTime) && truckArrivalTime.isBefore(latestAllowedTime)) {
                // If the truck's arrival time is within the allowed window, open the gate
                log.info("Truck arrived on time at: {}", truckArrivalTime);
                Activity activity = new Activity(
                        new ActivityId(appointment.getId(), UUID.randomUUID()),
                        ActivityType.ON_SITE,
                        truckArrivalTime,
                        AppointmentStatus.SCHEDULED,
                        appointment.getWarehouseId(),
                        appointment.getTruck()
                );
                updatedAppointment.forEach(port -> port.activityCreated(appointment, activity));
                return Optional.of(arrival);
            } else if (truckArrivalTime.isAfter(latestAllowedTime)) {
                // If the truck is late
                log.info("Truck arrived late at: {}", truckArrivalTime);
                return Optional.empty();
            }
        } else {
            log.info("No appointment found for license plate: {}", arrival.plate().licensePlate());
        }

        return Optional.empty();
    }
}
