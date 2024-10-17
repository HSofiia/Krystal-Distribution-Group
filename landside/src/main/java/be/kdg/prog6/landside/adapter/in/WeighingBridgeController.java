package be.kdg.prog6.landside.adapter.in;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.adapter.in.dto.TruckWeightDto;
import be.kdg.prog6.landside.domain.TruckWeight;
import be.kdg.prog6.landside.port.in.ArriveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.landside.port.in.WeighingBridgeCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/weighing-bridge")
public class WeighingBridgeController {
    private final ArriveWeighingBridgeUseCase arriveWeighingBridgeUseCase;
    private final LeaveWeighingBridgeUseCase leaveWeighingBridgeUseCase;

    public WeighingBridgeController(ArriveWeighingBridgeUseCase arriveWeighingBridgeUseCase, LeaveWeighingBridgeUseCase leaveWeighingBridgeUseCase) {
        this.arriveWeighingBridgeUseCase = arriveWeighingBridgeUseCase;
        this.leaveWeighingBridgeUseCase = leaveWeighingBridgeUseCase;
    }

    @PostMapping("/enter")
    public ResponseEntity<TruckWeightDto> truckArrivalWeight(@RequestBody TruckWeightDto truckWeightDto){

        WeighingBridgeCommand command = new WeighingBridgeCommand(
                truckWeightDto.getLicencePlate(),
                truckWeightDto.getWeight(),
                truckWeightDto.getTime()
        );


        TruckWeight result = arriveWeighingBridgeUseCase.arriveToWeighingBridge(command);

        return ResponseEntity.accepted().body(TruckWeightDto.of(result));
    }

    @PostMapping("/leaving")
    public ResponseEntity<TruckWeightDto> truckLeftWeight(@RequestBody TruckWeightDto truckWeightDto){

        WeighingBridgeCommand command = new WeighingBridgeCommand(
                truckWeightDto.getLicencePlate(),
                truckWeightDto.getWeight(),
                truckWeightDto.getTime()
        );


        TruckWeight result = leaveWeighingBridgeUseCase.leaveWeighingBridge(command);

        return ResponseEntity.accepted().body(TruckWeightDto.of(result));
    }
}
