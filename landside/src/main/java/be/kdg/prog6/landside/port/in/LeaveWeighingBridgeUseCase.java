package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.TruckWeight;
import be.kdg.prog6.landside.domain.WBT;

@FunctionalInterface
public interface LeaveWeighingBridgeUseCase {
    WBT leaveWeighingBridge(WeighingBridgeCommand weighingBridgeCommand);
}