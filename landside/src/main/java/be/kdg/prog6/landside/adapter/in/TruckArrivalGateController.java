package be.kdg.prog6.landside.adapter.in;

import be.kdg.prog6.landside.adapter.in.dto.TruckArrivalRequestDto;
import be.kdg.prog6.landside.domain.TruckArrival;
import be.kdg.prog6.landside.domain.TruckPlate;
import be.kdg.prog6.landside.port.in.TruckArrivalGateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/open-gate")
public class TruckArrivalGateController {
    private final TruckArrivalGateUseCase openGateUseCase;

    public TruckArrivalGateController(TruckArrivalGateUseCase openGateUseCase) {
        this.openGateUseCase = openGateUseCase;
    }

    @PostMapping("/arrived")
    public ResponseEntity<TruckArrivalRequestDto> openGate(@RequestBody TruckArrivalRequestDto truckArrivalRequest) {
        TruckArrival truckArrival = new TruckArrival(
                new TruckPlate(truckArrivalRequest.getLicensePlate()),
                truckArrivalRequest.getArrivalTime()
        );

        Optional<TruckArrival> result = openGateUseCase.openGate(truckArrival);

        if (result.isPresent()) {
            return ResponseEntity.accepted().body(TruckArrivalRequestDto.of(truckArrival));
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
