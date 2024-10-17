package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;

public interface UpdatedAppointmentPort{
    void activityCreated(Appointment appointment, Activity activity);
}
