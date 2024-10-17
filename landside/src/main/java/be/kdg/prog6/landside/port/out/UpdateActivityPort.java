package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.ActivityWindow;

import java.util.UUID;

public interface UpdateActivityPort {
    void updateActivity(Activity activity);
}
