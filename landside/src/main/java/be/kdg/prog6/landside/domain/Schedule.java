package be.kdg.prog6.landside.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Schedule {
    private final UUID id;
    private final LocalDate date;
    private final List<Appointment> scheduledAppointments;
    private final int maxTrucksPerHour;
    private final int MAX_NM_TRUCKS = 5;

    public Schedule(UUID id, LocalDate date, List<Appointment> scheduledAppointments, int maxTrucksPerHour) {
        this.id = id;
        this.date = date;
        this.scheduledAppointments = scheduledAppointments;
        this.maxTrucksPerHour = MAX_NM_TRUCKS;
    }

    public Optional<Appointment> scheduleAppointment(TruckPlate licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber) {
       return findFirstAvailableHour().map(localDateTime -> new Appointment(licensePlate, materialType,warehouseId,warehouseNumber, localDateTime));
    }

    // Method to find the first available hour where trucks scheduled are less than maxTrucksPerHour
    public Optional<LocalDateTime> findFirstAvailableHour() {
        // Iterate through each hour of the day
        for (int hour = 0; hour < 24; hour++) {
            LocalDateTime startOfHour = date.atTime(LocalTime.of(hour, 0));  // Start of the current hour

            // Count the number of trucks scheduled for this hour
            long trucksScheduledThisHour = scheduledAppointments.stream()
                    .filter(a -> isSameHour(a.getScheduledTime(), startOfHour))
                    .count();

            // If the number of trucks is less than maxTrucksPerHour, return this hour
            if (trucksScheduledThisHour < maxTrucksPerHour) {
                return Optional.of(startOfHour);
            }
        }

        // If no available hour is found, return empty
        return Optional.empty();
    }

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

    public LocalDate getDate() {
        return date;
    }
}
