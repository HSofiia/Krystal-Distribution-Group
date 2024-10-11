package be.kdg.prog6.landside.adapter.out.schedule;

import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
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

    public ScheduleJpaEntity() {
        this.id = UUID.randomUUID();
    }

    public ScheduleJpaEntity(UUID id) {
        this.id = id;
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
