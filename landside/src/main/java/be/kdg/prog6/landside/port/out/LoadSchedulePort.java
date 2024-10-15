package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

@FunctionalInterface
public interface LoadSchedulePort {
    Schedule loadScheduleByDate(LocalDateTime date);
//    void saveSchedule(Schedule schedule);
}
