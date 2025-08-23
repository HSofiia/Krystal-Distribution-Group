package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.api.dto.VesselInputDTO;
import be.kdg.prog6.port.in.RegisterShipArrivalUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arrivals")
public class ArrivalController {

    private final RegisterShipArrivalUseCase registerShipArrivalUseCase;

    public ArrivalController(RegisterShipArrivalUseCase registerShipArrivalUseCase
    ) {
        this.registerShipArrivalUseCase = registerShipArrivalUseCase;
    }


    @PostMapping("/{vesselNumber}")
    public ResponseEntity<?> inputInfoOnArrival(@PathVariable String vesselNumber,
                                                @RequestBody VesselInputDTO vesselInputDTO) {
        registerShipArrivalUseCase.inputInformation(vesselInputDTO.getPurchaseOrderNumber(), vesselNumber, vesselInputDTO.getCustomerEnterpriseNumber());
        return ResponseEntity.ok().body("Ship %s arrived successfully".formatted(vesselNumber));
    }

}
