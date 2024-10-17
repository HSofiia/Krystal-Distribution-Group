package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Appointment;

public interface SaveAppointmentPort {
    void update(Appointment appointment);
}
