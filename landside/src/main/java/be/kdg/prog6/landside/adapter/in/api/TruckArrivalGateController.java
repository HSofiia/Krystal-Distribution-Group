package be.kdg.prog6.landside.adapter.in.api;

import be.kdg.prog6.landside.adapter.in.api.dto.TruckArrivalRequestDto;
import be.kdg.prog6.landside.domain.TruckArrival;
import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.port.in.TruckArrivalGateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/open-gate")
public class TruckArrivalGateController {
    private final TruckArrivalGateUseCase openGateUseCase;

    public TruckArrivalGateController(TruckArrivalGateUseCase openGateUseCase) {
        this.openGateUseCase = openGateUseCase;
    }

    @PostMapping("/arrived")
    public ResponseEntity<TruckArrivalRequestDto> openGate(@RequestBody TruckArrivalRequestDto truckArrivalRequest) {
        Random random = new Random();
        int weighingBridgeNumber = random.nextInt(100);
        TruckArrival truckArrival = new TruckArrival(
                new TruckPlate(truckArrivalRequest.getLicensePlate()),
                truckArrivalRequest.getArrivalTime(),
                weighingBridgeNumber

        );

        Optional<TruckArrival> result = openGateUseCase.openGate(truckArrival);

        if (result.isPresent()) {
            return ResponseEntity.accepted().body(TruckArrivalRequestDto.of(truckArrival));
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
