package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Schedule;

import java.time.LocalDate;

@FunctionalInterface
public interface LoadSchedulePort {
    Schedule loadScheduleByDate(LocalDate date);
//    void saveSchedule(Schedule schedule);
}
