package be.kdg.prog6.family.adapter.out.appointment;

import be.kdg.prog6.family.domain.AppointmentStatus;
import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.Truck;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class AppointmentJpaEntity {

    @Id
    @Column(name = "appointmentId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "materialType")
    private MaterialType materialType;

    @Column(name = "warehouseId", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID warehouseId;

    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "scheduledTime")
    private LocalDateTime scheduledTime;

    @Column(name = "status")
    private AppointmentStatus status;

    public AppointmentJpaEntity(UUID id, String licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime, AppointmentStatus status) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }

    public AppointmentJpaEntity() {
    }
}
