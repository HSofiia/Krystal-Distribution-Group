package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.TruckOpenGateUseCase;
import be.kdg.prog6.landside.port.out.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.UpdatedAppointmentPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TruckOpenGateUseCaseImpl implements TruckOpenGateUseCase{

    private final LoadAppointmentPort loadAppointmentPort;
    private final List<UpdatedAppointmentPort> updatedAppointment;
    private static final int ALLOWED_TIME_WINDOW_MINUTES = 60;


    public TruckOpenGateUseCaseImpl(LoadAppointmentPort loadAppointmentPort, List<UpdatedAppointmentPort> updatedAppointment) {
        this.loadAppointmentPort = loadAppointmentPort;
        this.updatedAppointment = updatedAppointment;
    }

    @Override
    public Optional<TruckArrival> openGate(TruckArrival arrival) {

        Optional<Appointment> appointmentOpt = loadAppointmentPort.findAppointmentByLicencePlate(arrival.plate().licensePlate());

        if (appointmentOpt.isEmpty()) {
            return Optional.empty();
        }

        Appointment appointment = appointmentOpt.get();

        // Step 2: Check if the arrival time is within the allowed time window of the scheduled appointment
        LocalDateTime scheduledTime = appointment.getScheduledTime();
        LocalDateTime truckArrivalTime = arrival.arrivalTime();

        // Calculate the earliest and latest allowed times based on the ALLOWED_TIME_WINDOW_MINUTES
        LocalDateTime earliestAllowedTime = scheduledTime.minusMinutes(ALLOWED_TIME_WINDOW_MINUTES);
        LocalDateTime latestAllowedTime = scheduledTime.plusMinutes(ALLOWED_TIME_WINDOW_MINUTES);

        if (truckArrivalTime.isAfter(earliestAllowedTime) && truckArrivalTime.isBefore(latestAllowedTime)) {
            // If the truck's arrival time is within the allowed window, open the gate
            Activity activity = new Activity(
                    UUID.randomUUID(),
                    appointment.getId(),
                    ActivityType.ON_SITE,
                    LocalDateTime.now(),
                    AppointmentStatus.SCHEDULED,
                    appointment.getWarehouseId(),
                    appointment.getTruck()
            );

            // Notify all updatedAppointment ports
            updatedAppointment.forEach(port -> port.activityCreated(appointment, activity));

            return Optional.of(arrival);
        } else if (truckArrivalTime.isAfter(latestAllowedTime)) {
            Activity activity = new Activity(
                    UUID.randomUUID(),
                    appointment.getId(),
                    ActivityType.LATE,
                    LocalDateTime.now(),
                    AppointmentStatus.DENIED,
                    appointment.getWarehouseId(),
                    appointment.getTruck()
            );

            updatedAppointment.forEach(port -> port.activityCreated(appointment, activity));

            return Optional.empty();
        }

        return Optional.empty();
    }
}
