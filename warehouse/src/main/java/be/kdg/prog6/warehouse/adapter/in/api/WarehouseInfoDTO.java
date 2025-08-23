//package be.kdg.prog6.warehouse.adapter.in.api;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//public record WarehouseInfoDTO (String warehouseNumber, String seller, Double payloadAmount, Double maxCapacity, String materialType){
//    public static WarehouseInfoDTO from (Warehouse warehouse) {
//        return new WarehouseInfoDTO(
//                warehouse.getWarehouseNumber().number(),
//                warehouse.getSeller().getName(),
//                warehouse.getWarehouseMaterialAmount().amount(),
//                warehouse.getMaxCapacity(),
//                Arrays.stream(warehouse.getMaterialType().name().toLowerCase().split("_"))
//                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
//                        .collect(Collectors.joining(" "))
//        );
//    }
//}
