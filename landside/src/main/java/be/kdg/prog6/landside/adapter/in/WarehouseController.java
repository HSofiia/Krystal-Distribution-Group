//package be.kdg.prog6.landside.adapter.in;
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/warehouse")
//public class WarehouseController {
//        private final WarehouseService warehouseService;
//
//        @Autowired
//        public WarehouseController(WarehouseService warehouseService) {
//            this.warehouseService = warehouseService;
//        }
//
//        @GetMapping("/overview")
//        public ResponseEntity<WarehouseOverviewDto> getWarehouseOverview() {
//            WarehouseOverviewDto overview = warehouseService.getWarehouseOverview();
//            return ResponseEntity.ok(overview);
//        }
//
//}
