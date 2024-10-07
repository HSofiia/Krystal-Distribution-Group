package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.family.domain.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "schedule", catalog = "landside")
public class ScheduleJpaEntity {

    @Id
    @Column(name = "scheduleId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppointmentJpaEntity> scheduledAppointments;

    @Column(name = "maxTrucksPerHour")
    private int maxTrucksPerHour;

    public ScheduleJpaEntity(UUID id) {
        this.id = id;
    }

    public ScheduleJpaEntity() {
    }

    public ScheduleJpaEntity(UUID id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<AppointmentJpaEntity> getScheduledAppointments() {
        return scheduledAppointments;
    }

    public void setScheduledAppointments(List<AppointmentJpaEntity> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    public int getMaxTrucksPerHour() {
        return maxTrucksPerHour;
    }

    public void setMaxTrucksPerHour(int maxTrucksPerHour) {
        this.maxTrucksPerHour = maxTrucksPerHour;
    }
}
