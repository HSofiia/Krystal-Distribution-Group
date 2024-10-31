package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityAmountType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ActivityWindow {
    private int warehouseNumber;
    private List<PayloadActivity> activities;

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

    WarehouseCurrentCapacity calculateCapacity(final WarehouseCurrentCapacity currentCapacity){
        final LocalDateTime time = activities.stream()
                .max(Comparator.comparing(PayloadActivity::time))
                .orElseThrow()
                .time();
        final double amount = activities.stream().mapToDouble(PayloadActivity::getChangeToCapacity).sum();
        return new WarehouseCurrentCapacity(currentCapacity.number() + amount, time);
    }

    PayloadActivity addActivity(final ActivityAmountType type, final double amount) {
        final ActivityId activityId = new ActivityId(warehouseNumber, UUID.randomUUID());
        final PayloadActivity activity = new PayloadActivity(
                activityId,
                amount,
                LocalDateTime.now(),
                type
        );
        activities.add(activity);
        return activity;
    }
}
