//package be.kdg.prog6.warehouse.core;
//
//import be.kdg.prog6.warehouse.domain.Warehouse;
//import be.kdg.prog6.warehouse.port.in.WarehouseInfoUseCase;
//import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class WarehouseInfoUseCaseImpl implements WarehouseInfoUseCase {
//
//    private final LoadWarehousePort loadWarehousePort;
//
//    public WarehouseInfoUseCaseImpl(LoadWarehousePort loadWarehousePort) {
//        this.loadWarehousePort = loadWarehousePort;
//    }
//
//    @Override
//    public Warehouse warehouseInfo(int warehouseNumber) {
//        return loadWarehousePort.loadWarehouseByNumberSnapshot(warehouseNumber);
//    }
//
//    @Override
//    public List<Warehouse> allWarehousesInfo(){
//        return loadWarehousePort.loadAllWarehousesSnapshot();
//    }
//}
