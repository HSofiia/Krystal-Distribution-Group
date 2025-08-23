package be.kdg.prog6.adapter.out.shipmentOrder;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.FindSOPort;
import be.kdg.prog6.port.out.SaveSOPort;
import be.kdg.prog6.port.out.UpdateSOPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShipmentOrderAdapter implements SaveSOPort, FindSOPort, UpdateSOPort {

    private final ShipmentOrderJpaEntityRepository soRepository;

    public ShipmentOrderAdapter(ShipmentOrderJpaEntityRepository soRepository) {
        this.soRepository = soRepository;
    }


    @Override
    public void saveSO(ShipmentOrder shipmentOrder) {
        soRepository.save(toShipmentOrderJpaEntity(shipmentOrder));

    }

    @Override
    public ShipmentOrder getByVesselNumberAndNotStatus(String vesselNumber, OperationStatus statusNot) {
        ShipmentOrderJpaEntity shipmentOrderJpaEntity = soRepository.findByVesselNumberAndNotStatusFetched(vesselNumber, statusNot.name())
                .orElseThrow(() -> new EntityNotFoundException("No shipment order found for vessel number " + vesselNumber));
        return toShipmentOrderEntity(shipmentOrderJpaEntity);
    }

    @Override
    public List<ShipmentOrder> findAllShipmentOrderByBunkeringOperationDate(LocalDate date) {
        return soRepository.findAllByBunkeringOperationDate(date)
                .stream()
                .map(this::toShipmentOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentOrder updateShipmentOrder(ShipmentOrder shipmentOrder) {
        return toShipmentOrderEntity(soRepository.save(toShipmentOrderJpaEntity(shipmentOrder)));
    }

    public ShipmentOrderJpaEntity toShipmentOrderJpaEntity(ShipmentOrder shipmentOrder) {
        return new ShipmentOrderJpaEntity(
                shipmentOrder.getPoNumber().number(),
                shipmentOrder.getCustomerEnterpriseNumber(),
                shipmentOrder.getVesselNumber(),
                shipmentOrder.getArrivalDate(),
                shipmentOrder.getDepartureDate(),
                shipmentOrder.getBunkeringOperation().getBunkeringDate(),
                shipmentOrder.getInspectionOperation().getCompletedAt(),
                shipmentOrder.getInspectionOperation().getInspectorSignature(),
                shipmentOrder.getMatchedWithPO(),
                shipmentOrder.getOperationStatus().name()
        );
    }

    public ShipmentOrder toShipmentOrderEntity(ShipmentOrderJpaEntity shipmentOrderJpaEntity) {

        InspectionOperation io = new InspectionOperation(shipmentOrderJpaEntity.getInspectorSignature(), shipmentOrderJpaEntity.getInspectionOperationDate());
        BunkeringOperation bo = new BunkeringOperation(shipmentOrderJpaEntity.getBunkeringOperationDate());
        return new ShipmentOrder(
                new PONumber(shipmentOrderJpaEntity.getPoReferenceNumber()),
                shipmentOrderJpaEntity.getCustomerEnterpriseNumber(),
                shipmentOrderJpaEntity.getVesselNumber(),
                shipmentOrderJpaEntity.getArrivalDate(),
                shipmentOrderJpaEntity.getDepartureDate(),
                io,
                bo,
                shipmentOrderJpaEntity.getMatchedWithPO(),
                OperationStatus.valueOf(shipmentOrderJpaEntity.getShipmentStatus())
        );
    }
}
