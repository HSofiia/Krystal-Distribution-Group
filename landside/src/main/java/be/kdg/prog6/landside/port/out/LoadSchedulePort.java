package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Schedule;

import java.time.LocalDate;

@FunctionalInterface
public interface LoadSchedulePort {
    Schedule loadScheduleByDate(LocalDate date);
//    void saveSchedule(Schedule schedule);
}
