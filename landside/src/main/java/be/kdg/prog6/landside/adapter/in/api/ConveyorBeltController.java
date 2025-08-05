package be.kdg.prog6.landside.adapter.in.api;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.adapter.in.api.dto.PDTDto;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.port.in.DumpPayloadCommand;
import be.kdg.prog6.landside.port.in.DumpPayloadUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/conveyorBelt")
public class ConveyorBeltController {

    private final DumpPayloadUseCase dumpPayloadUseCase;

    public ConveyorBeltController(DumpPayloadUseCase dumpPayloadUseCase) {
        this.dumpPayloadUseCase = dumpPayloadUseCase;
    }

    @PostMapping("/{licensePlate}")
    public ResponseEntity<PDTDto> truckDumpPayload(@PathVariable String licensePlate) {
        DumpPayloadCommand command = new DumpPayloadCommand(new TruckPlate(licensePlate), LocalDateTime.now());
        Appointment updatedAppointment = dumpPayloadUseCase.dumpPayloadOnConveyorBelt(command);

        Random random = new Random();
        int dockNumber = random.nextInt(10000);

        PDTDto response = new PDTDto(
                updatedAppointment.getWarehouseNumber(),
                LocalDateTime.now(),
                updatedAppointment.getMaterialType().name(),
                dockNumber
        );

        return ResponseEntity.ok(response);
    }
}
