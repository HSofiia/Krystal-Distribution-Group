package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@FunctionalInterface
public interface LoadSchedulePort {
    Schedule loadScheduleByDate(LocalDateTime date);
//    void saveSchedule(Schedule schedule);
}
