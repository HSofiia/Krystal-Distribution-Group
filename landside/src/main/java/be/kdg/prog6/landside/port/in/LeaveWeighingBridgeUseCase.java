package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.TruckWeight;

@FunctionalInterface
public interface LeaveWeighingBridgeUseCase {
    TruckWeight leaveWeighingBridge(WeighingBridgeCommand weighingBridgeCommand);
}