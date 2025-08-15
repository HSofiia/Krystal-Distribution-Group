package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityType;

import java.time.LocalDateTime;

public record PayloadActivity(ActivityId activityId, double amount, LocalDateTime time, ActivityType activityType) {

    public double getChangeToCapacity() {
        return activityType == ActivityType.DELIVERY ? amount : -amount;
    }
}
