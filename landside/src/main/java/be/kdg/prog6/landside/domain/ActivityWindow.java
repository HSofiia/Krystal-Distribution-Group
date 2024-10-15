package be.kdg.prog6.landside.domain;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ActivityWindow {
    private final UUID appointmentId;
    private final List<Activity> activities;

    public ActivityWindow( UUID appointmentId, List<Activity> activities) {
        this.appointmentId = appointmentId;
        this.activities = activities;
    }

    List<Activity> getActivities() {
        return Collections.unmodifiableList(activities);
    }
}
