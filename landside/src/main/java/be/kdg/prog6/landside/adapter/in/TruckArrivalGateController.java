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
    public ResponseEntity<?> openGate(@RequestBody TruckArrivalRequestDto truckArrivalRequest) {
        // Create TruckArrival object from the request data
        TruckArrival truckArrival = new TruckArrival(
                new TruckPlate(truckArrivalRequest.getLicensePlate()),
                truckArrivalRequest.getArrivalTime()
        );

        // Call the use case to handle the arrival
        Optional<TruckArrival> result = openGateUseCase.openGate(truckArrival);

        // If the result is present, the arrival was accepted
        if (result.isPresent()) {
            return ResponseEntity.accepted().body(TruckArrivalRequestDto.of(truckArrival));
        } else {
            // If not, return a response indicating the truck is either early or late
            return ResponseEntity.status(403).body("Truck arrival time is not within the allowed window.");
        }
    }
}
