package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityType;

import java.time.LocalDateTime;

public class PayloadActivity {
    private ActivityId activityId;
    private double amount;
    private LocalDateTime time;
    private ActivityType activityType;

    public PayloadActivity(ActivityId activityId, double amount, LocalDateTime time, ActivityType activityType) {
        this.activityId = activityId;
        this.amount = amount;
        this.time = time;
        this.activityType = activityType;
    }

    public double getChangeToCapacity() {
        return activityType == ActivityType.DELIVERY ? amount : -amount;
    }

    public ActivityId getActivityId() {
        return activityId;
    }

    public void setActivityId(ActivityId activityId) {
        this.activityId = activityId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
}
