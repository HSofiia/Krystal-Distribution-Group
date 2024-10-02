package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.domain.Schedule;
import be.kdg.prog6.family.port.out.LoadSchedulePort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleDatabaseAdapter implements LoadSchedulePort {
    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleDatabaseAdapter(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }


    @Override
    public Schedule loadScheduleByDate(LocalDateTime date) {
        return scheduleJpaRepository.findScheduleByDate(date).map(this::toSchedule);
    }

    private Schedule toSchedule(final ScheduleJpaEntity scheduleJpaEntity){
        return new Schedule(scheduleJpaEntity.getId(),
                scheduleJpaEntity.getDate())
    }
}
