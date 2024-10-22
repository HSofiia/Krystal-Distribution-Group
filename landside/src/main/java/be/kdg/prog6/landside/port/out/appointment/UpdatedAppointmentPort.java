package be.kdg.prog6.landside.port.out.appointment;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.Appointment;

public interface UpdatedAppointmentPort{
    void activityCreated(Appointment appointment, Activity activity);
}
