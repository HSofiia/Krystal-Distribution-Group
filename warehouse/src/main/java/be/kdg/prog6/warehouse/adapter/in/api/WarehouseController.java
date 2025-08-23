package be.kdg.prog6.warehouse.adapter.in.api;

import be.kdg.prog6.warehouse.port.in.WarehouseInfoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseInfoUseCase warehouseInfoUseCase;

    public WarehouseController(WarehouseInfoUseCase warehouseInfoUseCase) {
        this.warehouseInfoUseCase = warehouseInfoUseCase;
    }

    @GetMapping("/{warehouseNumber}")
    public ResponseEntity<WarehouseDTO> getWarehouseByNumber(@PathVariable int warehouseNumber) {
        WarehouseDTO warehouseInfo = WarehouseDTO.from(warehouseInfoUseCase.warehouseInfo(warehouseNumber));
        return ResponseEntity.ok().body(warehouseInfo);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getWarehouses() {
        List<WarehouseDTO> warehouseInfoList = warehouseInfoUseCase
                .allWarehousesInfo()
                .stream()
                .map(WarehouseDTO::from)
                .toList();

        if (warehouseInfoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(warehouseInfoList);
    }
}
