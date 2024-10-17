package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.TruckWeight;

@FunctionalInterface
public interface ArriveWeighingBridgeUseCase {
    TruckWeight arriveToWeighingBridge(WeighingBridgeCommand weighingBridgeCommand);
}