package be.kdg.prog6.landside.domain;

import java.util.Collections;
import java.util.List;

public class ActivityWindow {
    private final TruckPlate licencePlate;
    private final List<Activity> activities;

    public ActivityWindow(TruckPlate licencePlate, List<Activity> activities) {
        this.licencePlate = licencePlate;
        this.activities = activities;
    }

//    Balance calculateBalance() {
//        final LocalDateTime time = activities.stream()
//            .max(Comparator.comparing(Activity::time))
//            .orElseThrow()
//            .time();
//        final int amount = activities.stream().mapToInt(Activity::getChangeToBalance).sum();
//        return new Balance(time, amount);
//    }
//
//    Activity addActivity(final ActivityType type, final int amount) {
//        final ActivityId activityId = new ActivityId(ownerId, UUID.randomUUID());
//        final Activity activity = new Activity(
//            activityId,
//            LocalDateTime.now(),
//            type,
//            amount
//        );
//        activities.add(activity);
//        return activity;
//    }

    List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
    }
}
