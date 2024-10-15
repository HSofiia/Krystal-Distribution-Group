package be.kdg.prog6.landside.adapter.out.appointment;

import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.landside.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.MaterialType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appointments", catalog = "landside")
public class AppointmentJpaEntity {

    @Id
    @Column(name = "appointmentId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "materialType")
    @Enumerated(value = EnumType.STRING)
    private MaterialType materialType;

    @Column(name = "warehouseId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "scheduledTime")
    private LocalDateTime scheduledTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId", nullable = false)
    private ScheduleJpaEntity schedule;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus status;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<AppointmentActivityJpaEntity> activities;


    public AppointmentJpaEntity(UUID id, String licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime, AppointmentStatus status) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }

    public AppointmentJpaEntity(UUID id, String licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime, ScheduleJpaEntity schedule, AppointmentStatus status, List<AppointmentActivityJpaEntity> activities) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.schedule = schedule;
        this.status = status;
        this.activities = activities;
    }

    public AppointmentJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public ScheduleJpaEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleJpaEntity schedule) {
        this.schedule = schedule;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public List<AppointmentActivityJpaEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<AppointmentActivityJpaEntity> activities) {
        this.activities = activities;
    }
}
