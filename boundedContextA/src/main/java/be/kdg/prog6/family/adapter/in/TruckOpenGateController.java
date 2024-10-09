package be.kdg.prog6.family.adapter.in;

import be.kdg.prog6.family.domain.TruckArrival;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.in.TruckOpenGateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/open-gate")
public class TruckOpenGateController {
    private final TruckOpenGateUseCase openGateUseCase;

    public TruckOpenGateController(TruckOpenGateUseCase openGateUseCase) {
        this.openGateUseCase = openGateUseCase;
    }

    @PostMapping("/{licensePlate}/arrived")
    public ResponseEntity<?> openGate(@PathVariable String licensePlate){
        LocalDateTime arriveDate = LocalDateTime.parse("2024-10-11T00:00:00");
        TruckArrival truckArrival = new TruckArrival(new TruckPlate(licensePlate), arriveDate);
        openGateUseCase.openGate(truckArrival);

        return ResponseEntity.ok(
                String.format("Truck %s arrived at %s", licensePlate, arriveDate)
        );
    }
}
