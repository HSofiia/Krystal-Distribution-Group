package be.kdg.prog4.adapter.out.warehouse;

import be.kdg.prog4.adapter.out.payload.PayloadJpaEntity;
import be.kdg.prog4.adapter.out.payload.PayloadJpaRepository;
import be.kdg.prog4.domain.Payload;
import be.kdg.prog4.domain.Warehouse;
import be.kdg.prog4.port.out.LoadWarehousePort;
import be.kdg.prog4.port.out.UpdateStoragePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class WarehouseDatabaseAdapter implements LoadWarehousePort, UpdateStoragePort {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final PayloadJpaRepository payloadJpaRepository;

    public WarehouseDatabaseAdapter(WarehouseJpaRepository warehouseJpaRepository, PayloadJpaRepository payloadJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.payloadJpaRepository = payloadJpaRepository;
    }

    @Override
    public Warehouse loadBySellerIdAndMaterialType(SellerId sellerId, MaterialType materialType){
        UUID sellerUuid = sellerId.sellerId();
        return warehouseJpaRepository.findBySellerIdAndMaterialTypeFetched(sellerUuid, materialType.name())
                .map(this::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("No warehouse found for seller %s and material %s"
                        .formatted(sellerId, materialType)));
    }

    @Override
    public List<Warehouse> loadWarehousesBySellerId(SellerId sellerId){
        return warehouseJpaRepository.findAllBySellerIdFetched(sellerId.sellerId()).stream().map(this::toDomain).toList();
    }

    @Override
    public void update(Warehouse warehouse){
        WarehouseJpaEntity jpa = warehouseJpaRepository.findBySellerIdAndMaterialTypeFetched(warehouse.getSellerId().sellerId(), warehouse.getMaterialType().name())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Warehouse %s not found".formatted(warehouse.getWarehouseNumber())));

        payloadJpaRepository.deleteByWarehouseNumber(warehouse.getWarehouseNumber());

        for (Payload payload : warehouse.getPayloads()) {
            PayloadJpaEntity p = new PayloadJpaEntity();
            p.setPayloadId(UUID.randomUUID());
            p.setNetTons(payload.getNetTons());
            p.setDeliveredAt(payload.getDeliveredAt());
            p.setWarehouse(jpa);
            payloadJpaRepository.save(p);
        }

        warehouseJpaRepository.save(jpa);
    }

    private Warehouse toDomain(WarehouseJpaEntity entity) {
        return new Warehouse(
                entity.getWarehouseNumber(),
                new SellerId(entity.getSellerId()),
                MaterialType.valueOf(entity.getMaterialType()),
                entity.getPayloads().stream()
                        .map(this::toDomainPayload)
                        .toList()
        );
    }

    private Payload toDomainPayload(PayloadJpaEntity entity) {
        return new Payload(
                entity.getNetTons(),
                entity.getDeliveredAt()
        );
    }


}
