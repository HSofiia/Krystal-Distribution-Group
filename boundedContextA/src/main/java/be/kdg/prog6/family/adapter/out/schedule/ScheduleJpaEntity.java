package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.domain.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class ScheduleJpaEntity {

    @Id
    @Column(name = "scheduleId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppointmentJpaEntity> scheduledAppointments;  // Changed from Map to List

    @Column(name = "maxTrucksPerHour")
    private int maxTrucksPerHour;


    public ScheduleJpaEntity() {
    }

    public ScheduleJpaEntity(UUID id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }
}
