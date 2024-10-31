//package be.kdg.prog6.warehouse.core;
//
//import be.kdg.prog6.warehouse.domain.Warehouse;
//import be.kdg.prog6.warehouse.domain.WarehouseActivity;
//import be.kdg.prog6.warehouse.port.in.WarehouseInfoUseCase;
//import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
//
//public class WarehouseInfoUseCaseImpl implements WarehouseInfoUseCase {
//
//    private final LoadWarehousePort loadWarehousePort;
//
//    public WarehouseInfoUseCaseImpl(LoadWarehousePort loadWarehousePort) {
//        this.loadWarehousePort = loadWarehousePort;
//    }
//
//    @Override
//    public WarehouseActivity warehouseInfo(int warehouseNumber) {
//        Warehouse warehouse = loadWarehousePort.loadWarehouseByNumber(warehouseNumber).orElseThrow();
//        return new WarehouseActivity(
//                warehouseNumber,
//                warehouse.getSeller(),
//                warehouse.getAmountReceived(),
//                warehouse.getMaterialType()
//        );
//    }
//}
