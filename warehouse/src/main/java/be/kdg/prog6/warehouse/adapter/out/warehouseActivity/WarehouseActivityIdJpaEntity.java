package be.kdg.prog6.warehouse.adapter.out.warehouseActivity;

import be.kdg.prog6.warehouse.domain.ActivityId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class WarehouseActivityIdJpaEntity implements Serializable {
    @Column(name = "warehouseNumber")
    private int warehouseNumber;

    @Column(name = "activity_id", columnDefinition = "varchar(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID activityId;

    public WarehouseActivityIdJpaEntity(int warehouseNumber, UUID activityId) {
        this.warehouseNumber = warehouseNumber;
        this.activityId = activityId;
    }

    public WarehouseActivityIdJpaEntity() {
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public UUID getActivityId() {
        return activityId;
    }

    public void setActivityId(UUID activityId) {
        this.activityId = activityId;
    }

    public static WarehouseActivityIdJpaEntity of(final ActivityId activityId) {
        return new WarehouseActivityIdJpaEntity(activityId.warehouseNumber(), activityId.id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseActivityIdJpaEntity that = (WarehouseActivityIdJpaEntity) o;
        return warehouseNumber == that.warehouseNumber && Objects.equals(activityId, that.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseNumber, activityId);
    }
}
