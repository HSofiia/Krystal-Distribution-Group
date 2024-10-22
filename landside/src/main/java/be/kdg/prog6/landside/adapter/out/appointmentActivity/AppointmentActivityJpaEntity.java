package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.landside.domain.ActivityType;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.TruckStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointmentActivity", catalog = "landside")
public class AppointmentActivityJpaEntity {

    @EmbeddedId
    private AppointmentActivityIdJpaEntity id;

    @Column(name = "activityType")
    @Enumerated(value = EnumType.STRING)
    ActivityType activityType;

    @Column(name = "time")
    LocalDateTime time;

    @Column(name = "truckStatus")
    @Enumerated(value = EnumType.STRING)
    TruckStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouseId", nullable = false)
    WarehouseJpaEntity warehouseId;

    @Column(name = "licensePlate")
    String licencePlate;

    @ManyToOne
    @JoinColumn(name = "appointmentId", referencedColumnName = "appointmentId", insertable = false, updatable = false)
    private AppointmentJpaEntity appointment;

    public AppointmentActivityJpaEntity(AppointmentActivityIdJpaEntity id, ActivityType activityType, LocalDateTime time, TruckStatus status, WarehouseJpaEntity warehouseId, String licencePlate) {
        this.id = id;
        this.activityType = activityType;
        this.time = time;
        this.status = status;
        this.warehouseId = warehouseId;
        this.licencePlate = licencePlate;
    }

    public AppointmentActivityJpaEntity(AppointmentActivityIdJpaEntity id) {
        this.id = id;
    }

    public AppointmentActivityJpaEntity() {
    }

    public AppointmentActivityIdJpaEntity getId() {
        return id;
    }

    public void setId(AppointmentActivityIdJpaEntity id) {
        this.id = id;
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

    public TruckStatus getStatus() {
        return status;
    }

    public void setStatus(TruckStatus status) {
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
