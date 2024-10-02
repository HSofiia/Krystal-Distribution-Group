package be.kdg.prog6.family.domain;

import java.time.LocalDateTime;
import java.util.*;

public class Schedule {
    private final UUID id;
    private final LocalDateTime date;
    private final Map<LocalDateTime, Appointment> scheduledAppointments;
    private final int maxTrucksPerHour;

    public Schedule(UUID id, LocalDateTime date, Map<LocalDateTime, Appointment> scheduledAppointments, int maxTrucksPerHour) {
        this.id = id;
        this.date = date;
        this.scheduledAppointments = scheduledAppointments;
        this.maxTrucksPerHour = 1;
    }

    public Optional<Appointment> scheduleAppointment(Truck licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber) {
        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0); // Get the current hour without minutes and seconds

        // Check if there are already appointments for the current hour
        if (scheduledAppointments.containsKey(currentHour)) {
            return Optional.empty();  // No capacity left for this hour
        }

        // Create a new appointment and book the time slot
        Appointment newAppointment = new Appointment(licensePlate, materialType, warehouseId, warehouseNumber, currentHour);
        scheduledAppointments.put(currentHour, newAppointment);

        return Optional.of(newAppointment);
    }

    public Collection<Appointment> getAppointments() {
        return Collections.unmodifiableCollection(scheduledAppointments.values());
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
