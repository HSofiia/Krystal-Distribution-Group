package be.kdg.prog6.warehouse.adapter.out.po;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.Measure;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.warehouse.adapter.out.orderLine.OrderLineJpaEntity;
import be.kdg.prog6.warehouse.adapter.out.seller.SellerJpaEntity;
import be.kdg.prog6.warehouse.domain.OrderLine;
import be.kdg.prog6.warehouse.domain.PO;
import be.kdg.prog6.warehouse.domain.PONumber;
import be.kdg.prog6.warehouse.domain.Seller;
import be.kdg.prog6.warehouse.port.out.LoadPOPort;
import be.kdg.prog6.warehouse.port.out.UpdatePOPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class POAdapter implements UpdatePOPort, LoadPOPort {
    private final POJpaRepository poJpaRepository;

    public POAdapter(POJpaRepository poJpaRepository) {
        this.poJpaRepository = poJpaRepository;
    }

    @Override
    public void update(PO purchaseOrder){
        POJpaEntity purchaseOrderJpaEntity = poJpaRepository.findById(purchaseOrder.poNumber().number())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Purchase order %s not found".formatted(purchaseOrder.poNumber())));
        purchaseOrderJpaEntity.setOrderStatus(purchaseOrder.status().name());
        poJpaRepository.save(purchaseOrderJpaEntity);
    }

    @Override
    public PO getByPurchaseOrderNumber(String purchaseOrderNumber){
        POJpaEntity purchaseOrderJpaEntity = poJpaRepository
                .findByPurchaseOrderNumberFetched(purchaseOrderNumber)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Purchase order %s not found".formatted(purchaseOrderNumber)));

        List<OrderLine> orderLines = toOrderLineList(purchaseOrderJpaEntity.getOrderLines());

        return fromJpaPo(purchaseOrderJpaEntity, orderLines);
    }

    @Override
    public List<PO> getAllPurchaseOrders(){
        return poJpaRepository.findAllWithSellerFetched()
                .stream()
                .map(this::toPO)
                .collect(Collectors.toList());
    }

    public List<OrderLine> toOrderLineList(List<OrderLineJpaEntity> orderLines) {
        return orderLines
                .stream()
                .map(this::toOrderLine)
                .collect(Collectors.toList());
    }

    public OrderLine toOrderLine(OrderLineJpaEntity orderLineJpaEntity) {
        return new OrderLine(
                MaterialType.valueOf(orderLineJpaEntity.getMaterialType()),
                orderLineJpaEntity.getAmount(),
                Measure.valueOf(orderLineJpaEntity.getUom())
        );
    }

    public PO fromJpaPo(POJpaEntity poJpaEntity, List<OrderLine> orderLines) {
        return new PO(
                fromJpaSeller(poJpaEntity.getSeller()),
                orderLines,
                new PONumber(poJpaEntity.getPoNumber()),
                PO.OrderStatus.valueOf(poJpaEntity.getOrderStatus()),
                poJpaEntity.getReceivedDateTime());
    }

    public PO toPO(POJpaEntity poJpaEntity) {
        return new PO(
                fromJpaSeller(poJpaEntity.getSeller()),
                new ArrayList<>(),
                new PONumber(poJpaEntity.getPoNumber()),
                PO.OrderStatus.valueOf(poJpaEntity.getOrderStatus()),
                poJpaEntity.getReceivedDateTime()

        );
    }

    public static Seller fromJpaSeller(SellerJpaEntity sellerJpaEntity) {
        if (sellerJpaEntity == null) {
            throw new IllegalStateException("Warehouse has no seller linked");
        }
        return new Seller(new SellerId(sellerJpaEntity.getId()), sellerJpaEntity.getSellerName());
    }
}
