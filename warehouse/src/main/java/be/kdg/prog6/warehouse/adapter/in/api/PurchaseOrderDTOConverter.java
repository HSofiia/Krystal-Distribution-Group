//package be.kdg.prog6.warehouse.adapter.in.api;
//
//import be.kdg.prog6.domain.PurchaseOrder;
//
//public class PurchaseOrderDTOConverter {
//    public static PurchaseOrderDTO convert(PurchaseOrder purchaseOrder) {
//        return new PurchaseOrderDTO(
//                purchaseOrder.status().name(),
//                purchaseOrder.poNumber().number(),
//                purchaseOrder.getReceivedDateTime(),
//                purchaseOrder.getSeller().getName()
//        );
//    }
//}
