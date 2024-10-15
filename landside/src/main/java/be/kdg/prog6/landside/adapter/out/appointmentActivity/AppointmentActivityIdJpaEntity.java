package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import be.kdg.prog6.landside.domain.ActivityId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AppointmentActivityIdJpaEntity implements Serializable {

    @Column(name = "appointmentId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID appointmentId;

    @Column(name = "activityId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID activityId;

    public AppointmentActivityIdJpaEntity(UUID appointmentId, UUID activityId) {
        this.appointmentId = appointmentId;
        this.activityId = activityId;
    }

    public AppointmentActivityIdJpaEntity() {
    }

    public static AppointmentActivityIdJpaEntity of(final ActivityId activityId) {
        return new AppointmentActivityIdJpaEntity(activityId.appointmentId(), activityId.id());
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public UUID getActivityId() {
        return activityId;
    }

    public void setActivityId(UUID activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentActivityIdJpaEntity that = (AppointmentActivityIdJpaEntity) o;
        return Objects.equals(appointmentId, that.appointmentId) && Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, activityId);
    }
}
