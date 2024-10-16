package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.*;

public class Schedule {
    private final UUID id;
    private final LocalDateTime date;
    private final List<Appointment> scheduledAppointments;
    private final int maxTrucksPerHour;
    private final int MAX_NM_TRUCKS = 5;

    public Schedule(UUID id, LocalDateTime date, List<Appointment> scheduledAppointments) {
        this.id = id;
        this.date = date;
        this.scheduledAppointments = scheduledAppointments;
        this.maxTrucksPerHour = MAX_NM_TRUCKS;
    }

    public Optional<Appointment> scheduleAppointment(
            TruckPlate licensePlate,
            MaterialType materialType,
            UUID warehouseId,
            int warehouseNumber,
            LocalDateTime requestedTime) {

        // Check if the requested time exceeds the max number of trucks allowed
        long trucksScheduledThisHour = scheduledAppointments.stream()
                .filter(a -> isSameHour(a.getScheduledTime(), requestedTime))
                .count();

        if (trucksScheduledThisHour < maxTrucksPerHour) {
            // If it's below the limit, create a new appointment
            ActivityWindow activityWindow = new ActivityWindow(UUID.randomUUID(), Collections.emptyList());
            return Optional.of(new Appointment(
                    UUID.randomUUID(), // generate a new appointment ID
                    licensePlate,
                    materialType,
                    warehouseId,
                    warehouseNumber,
                    requestedTime,
                    AppointmentStatus.SCHEDULED,
                    activityWindow
            ));
        } else {
            // Return empty if the requested time is overbooked
            return Optional.empty();
        }
    }


//    // Method to find the first available hour where trucks scheduled are less than maxTrucksPerHour
//    public Optional<LocalDateTime> findFirstAvailableHour() {
//        // Iterate through each hour of the day
//        for (int hour = 0; hour < 24; hour++) {
//            LocalDateTime startOfHour = LocalDateTime.of(LocalDate.from(date), LocalTime.of(hour, 0));  // Combine date and hour into LocalDateTime
//
//            // Count the number of trucks scheduled for this hour
//            long trucksScheduledThisHour = scheduledAppointments.stream()
//                    .filter(a -> isSameHour(a.getScheduledTime(), startOfHour))
//                    .count();
//
//            // If the number of trucks is less than maxTrucksPerHour, return this hour
//            if (trucksScheduledThisHour < maxTrucksPerHour) {
//                return Optional.of(startOfHour);
//            }
//        }
//
//        return Optional.empty();
//    }

    // Helper method to check if two times are within the same hour
    private boolean isSameHour(LocalDateTime time1, LocalDateTime time2) {
        return time1.getYear() == time2.getYear()
                && time1.getMonth() == time2.getMonth()
                && time1.getDayOfMonth() == time2.getDayOfMonth()
                && time1.getHour() == time2.getHour();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
