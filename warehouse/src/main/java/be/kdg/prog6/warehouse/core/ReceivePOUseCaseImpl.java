//package be.kdg.prog6.warehouse.core;
//
//import be.kdg.prog6.warehouse.port.in.ReceivePOUseCase;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReceivePOUseCaseImpl implements ReceivePOUseCase {
//
//    private final PurchaseOrderSavedPort purchaseOrderSavedPort;
//    private final PurchaseOrderFoundPort purchaseOrderFoundPort;
//    private final SellerFoundPort sellerFoundPort;
//
//    public ReceivePOUseCaseImpl(PurchaseOrderSavedPort purchaseOrderSavedPort, PurchaseOrderFoundPort purchaseOrderFoundPort, SellerFoundPort sellerFoundPort) {
//        this.purchaseOrderSavedPort = purchaseOrderSavedPort;
//        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
//        this.sellerFoundPort = sellerFoundPort;
//    }
//
//    @Override
//    public void receivePO(PurchaseOrder purchaseOrder) {
//        try {
//            purchaseOrderFoundPort.getByPurchaseOrderNumber(purchaseOrder.poNumber().number());
//            throw new PurchaseOrderExistsException(
//                    "Purchase order %s already exists".formatted(
//                            purchaseOrder.poNumber().number()));
//
//        } catch (PurchaseOrderNotFoundException e) {
//
//            Seller seller = sellerFoundPort.getById(purchaseOrder.getSeller().getSellerId());
//            purchaseOrder.setSeller(seller);
//
//            purchaseOrderSavedPort.savePurchaseOrder(purchaseOrder);
//        }
//
//    }
//}
