package be.kdg.prog6.warehouse.adapter.out.payload;

import be.kdg.prog6.warehouse.port.in.PayloadCommand;
import be.kdg.prog6.warehouse.port.out.SaveWarehousePayloadRecordPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PayloadRecordDatabaseAdapter implements SaveWarehousePayloadRecordPort {

    private final PayloadRecordJpaRepository payloadRecordRepository;

    public PayloadRecordDatabaseAdapter(PayloadRecordJpaRepository payloadRecordRepository) {
        this.payloadRecordRepository = payloadRecordRepository;
    }


    @Override
    public void saveOrUpdatePDT(PayloadCommand command) {
        Optional<PayloadActivityJpaEntity> existingPDT = payloadRecordRepository.findFirstByWarehouseNumberAndNetWeight(
                command.warehouseNumber(),
                0.0
        );

        if(existingPDT.isEmpty()) {
            payloadRecordRepository.save(toEntity(command));
        } else {
            PayloadActivityJpaEntity entity = existingPDT.get();
            entity.setNetWeight(command.netWeight());
            payloadRecordRepository.save(entity);
        }
    }


    private PayloadActivityJpaEntity toEntity(PayloadCommand command){
        return new PayloadActivityJpaEntity(
                UUID.randomUUID(),
                command.warehouseNumber(),
                command.netWeight(),
                command.time(),
                command.activityType()
        );
    }
}
