package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityType;

import java.time.LocalDateTime;
import java.util.*;

public class ActivityWindow {
    private int warehouseNumber;
    private final List<PayloadActivity> activities;

    public ActivityWindow(int warehouseNumber, List<PayloadActivity> activities) {
        this.warehouseNumber = warehouseNumber;
        this.activities = activities;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public List<PayloadActivity> getActivities() {
        return Collections.unmodifiableList(activities);
    }

    public Optional<PayloadActivity> findPendingPayloadActivity() {
        return activities.stream()
                .filter(a -> a.getActivityType() == ActivityType.DELIVERY && a.getAmount() == 0.0)
                .findFirst();
    }

    public double addChangeToCapacitySnapshot(LocalDateTime time) {
        LocalDateTime snapshot = (time != null) ? time : LocalDateTime.MIN;

        return activities.stream()
                .filter(a -> a.getTime() != null && !a.getTime().isBefore(snapshot)) // >= snapshot
                .mapToDouble(PayloadActivity::getChangeToCapacity)
                .sum();
    }

    public Optional<LocalDateTime> findLastActivitySnapshot(LocalDateTime time) {
        LocalDateTime snapshot = (time != null) ? time : LocalDateTime.MIN;

        return activities.stream()
                .map(PayloadActivity::getTime)
                .filter(t -> t != null && !t.isBefore(snapshot))
                .max(LocalDateTime::compareTo);
    }

    PayloadActivity addActivity(final ActivityType type, final double amount, LocalDateTime time) {
        final ActivityId activityId = new ActivityId(warehouseNumber, UUID.randomUUID());
        final PayloadActivity activity = new PayloadActivity(
                activityId,
                amount,
                time,
                type
        );
        activities.add(activity);
        return activity;
    }
}
