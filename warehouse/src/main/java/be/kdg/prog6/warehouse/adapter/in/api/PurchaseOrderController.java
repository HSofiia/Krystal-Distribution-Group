//package be.kdg.prog6.warehouse.adapter.in.api;
//
//import be.kdg.prog6.domain.PurchaseOrder;
//import be.kdg.prog6.port.in.CheckPurchaseOrdersStatusesUseCase;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/purchaseOrders")
//public class PurchaseOrderController {
//
//    private final CheckPurchaseOrdersStatusesUseCase checkPurchaseOrdersStatusesUseCase;
//
//    public PurchaseOrderController(CheckPurchaseOrdersStatusesUseCase checkPurchaseOrdersStatusesUseCase) {
//        this.checkPurchaseOrdersStatusesUseCase = checkPurchaseOrdersStatusesUseCase;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
//
//        List<PurchaseOrder> purchaseOrders = checkPurchaseOrdersStatusesUseCase.getAllPurchaseOrders();
//        List<PurchaseOrderDTO> purchaseOrderDTOS = purchaseOrders.stream().map(PurchaseOrderDTOConverter::convert).toList();
//        if (purchaseOrderDTOS.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok().body(purchaseOrderDTOS);
//    }
//}
