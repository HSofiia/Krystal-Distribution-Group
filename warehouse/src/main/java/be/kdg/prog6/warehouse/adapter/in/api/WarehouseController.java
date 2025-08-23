//package be.kdg.prog6.warehouse.adapter.in.api;
//
//import be.kdg.prog6.domain.WarehouseNumber;
//import be.kdg.prog6.port.in.GetInfosOfWarehousesUseCase;
//import be.kdg.prog6.port.in.GetWarehouseInfoUseCase;
//import be.kdg.prog6.port.in.RecordWarehouseStateUseCase;
//import be.kdg.prog6.warehouse.port.in.WarehouseInfoUseCase;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/warehouses")
//public class WarehouseController {
//
//    private final WarehouseInfoUseCase warehouseInfoUseCase;
//
//    public WarehouseController(WarehouseInfoUseCase warehouseInfoUseCase) {
//        this.warehouseInfoUseCase = warehouseInfoUseCase;
//    }
//
//    @GetMapping("/{warehouseNumber}")
//    public ResponseEntity<WarehouseInfoDTO> getWarehouseInfo(@PathVariable String warehouseNumber) {
////        WarehouseInfoDTO warehouseInfo = WarehouseInfoDTO.from(getWarehouseInfoUseCase.getWarehouseInfo(new WarehouseNumber(warehouseNumber)));
//        return ResponseEntity.ok().body(warehouseNumber);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<WarehouseInfoDTO>> getWarehouses() {
//        List<WarehouseInfoDTO> warehouseInfoList = warehouseInfoUseCase
//                .allWarehousesInfo()
//                .stream()
//                .map(this::from)
//                .toList();
//
//        if (warehouseInfoList.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok().body(warehouseInfoList);
//    }
//}
