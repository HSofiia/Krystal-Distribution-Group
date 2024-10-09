package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Activity;
import be.kdg.prog6.family.domain.Appointment;

public interface UpdatedAppointmentPort{
    void activityCreated(Appointment appointment, Activity activity);
}
