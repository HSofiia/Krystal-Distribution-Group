package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityAmountType;

import java.time.LocalDateTime;

public record PayloadActivity(ActivityId activityId, double amount, LocalDateTime time, ActivityAmountType activityType) {

    public double getChangeToCapacity() {
        return activityType == ActivityAmountType.DELIVERY ? amount : -amount;
    }
}
