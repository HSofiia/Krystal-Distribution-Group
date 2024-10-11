package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.landside.domain.ActivityType;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointmentActivity", catalog = "landside")
public class AppointmentActivityJpaEntity {

    @Id
    @Column(name = "activityId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointmentId", nullable = false)
    AppointmentJpaEntity appointmentId;

    @Column(name = "activityType")
    @Enumerated(value = EnumType.STRING)
    ActivityType activityType;

    @Column(name = "time")
    LocalDateTime time;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseId", nullable = false)
    WarehouseJpaEntity warehouseId;

    @Column(name = "licensePlate")
    String licencePlate;

    public AppointmentActivityJpaEntity(UUID id, AppointmentJpaEntity appointmentId, ActivityType activityType, LocalDateTime time, AppointmentStatus status, WarehouseJpaEntity warehouseId, String licencePlate) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.activityType = activityType;
        this.time = time;
        this.status = status;
        this.warehouseId = warehouseId;
        this.licencePlate = licencePlate;
    }

    public AppointmentActivityJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AppointmentJpaEntity getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(AppointmentJpaEntity appointmentId) {
        this.appointmentId = appointmentId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public WarehouseJpaEntity getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseJpaEntity warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
