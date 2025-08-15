package be.kdg.prog6.warehouse.adapter.out.payload;

import be.kdg.prog6.warehouse.adapter.out.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.warehouse.WarehouseJpaRepository;
import be.kdg.prog6.warehouse.domain.PayloadActivity;
import be.kdg.prog6.warehouse.port.out.SavePayloadActivityPort;
import be.kdg.prog6.warehouse.port.out.UpdatePayloadActivityPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PayloadRecordDatabaseAdapter implements SavePayloadActivityPort, UpdatePayloadActivityPort {

    private final PayloadRecordJpaRepository payloadRecordRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public PayloadRecordDatabaseAdapter(PayloadRecordJpaRepository payloadRecordRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.payloadRecordRepository = payloadRecordRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    @Override
    public void savePayload(PayloadActivity payloadActivity, int warehouseNumber) {
        PayloadActivityJpaEntity existingPayloadJpa = toEntity(payloadActivity);
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.getReferenceById(warehouseNumber);

        existingPayloadJpa.setWarehouse(warehouseJpaEntity);

        payloadRecordRepository.save(existingPayloadJpa);
    }

    @Override
    public void updatePayload(PayloadActivity payloadActivity, int warehouseNumber, double netWeight){
        PayloadActivityJpaEntity payloadActivityJpaEntity = payloadRecordRepository
                .findFirstByWarehouseAndTime(
                        warehouseNumber,
                        payloadActivity.amount(),
                        payloadActivity.time())
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("PayloadActivity not found"));

        payloadActivityJpaEntity.setNetWeight(netWeight);
        payloadRecordRepository.save(payloadActivityJpaEntity);
    }

    private PayloadActivityJpaEntity toEntity(PayloadActivity payloadActivity){
        return new PayloadActivityJpaEntity(
                UUID.randomUUID(),
                payloadActivity.amount(),
                payloadActivity.time(),
                payloadActivity.activityType()
        );
    }
}
