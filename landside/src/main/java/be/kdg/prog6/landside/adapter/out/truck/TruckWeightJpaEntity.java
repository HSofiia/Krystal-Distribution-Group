package be.kdg.prog6.landside.adapter.out.truck;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "truckWeight", catalog = "landside")
public class TruckWeightJpaEntity {

    @Id
    @Column(name = "id", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "licencePlate")
    String licencePlate;

    @Column(name = "weight")
    double weight;

    @Column(name = "time")
    LocalDateTime time;

    public TruckWeightJpaEntity(UUID id, String licencePlate, double weight, LocalDateTime time) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.weight = weight;
        this.time = time;
    }

    public TruckWeightJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
