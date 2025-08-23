package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.port.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    private final MatchSOAndPOUseCase matchSOAndPOUseCase;
    private final PlanBunkeringOperationUseCase planBunkeringOperationUseCase;
    private final CompleteInspectionUseCase completeInspectionUseCase;

    public VesselController(MatchSOAndPOUseCase matchSOAndPOUseCase, PlanBunkeringOperationUseCase planBunkeringOperationUseCase, CompleteInspectionUseCase completeInspectionUseCase
    ) {
        this.matchSOAndPOUseCase = matchSOAndPOUseCase;
        this.planBunkeringOperationUseCase = planBunkeringOperationUseCase;
        this.completeInspectionUseCase = completeInspectionUseCase;
    }


    @PostMapping("/{vesselNumber}/matchOrders")
    public ResponseEntity<?> matchSoPo(@PathVariable String vesselNumber){
        matchSOAndPOUseCase.matchSOAndPO(vesselNumber);
        return ResponseEntity.ok().body("SO and PO matched for vessel " + vesselNumber);
    }


    @PostMapping("/{vesselNumber}/bunkeringOperations/{date}")
    public ResponseEntity<?> scheduleBunkeringOperation(@PathVariable String vesselNumber, @PathVariable LocalDate date){
        planBunkeringOperationUseCase.planBO(vesselNumber, date);
        return ResponseEntity.ok().body("Bunkering operation for %s is scheduled for %s".formatted(vesselNumber, date));
    }

    @PostMapping("/{vesselNumber}/inspectionOperations/{signature}")
    public ResponseEntity<?> completeVesselInspection(@PathVariable String vesselNumber,
                                                      @PathVariable String signature){
        InspectionCommand command = new InspectionCommand(vesselNumber, signature, LocalDate.now());
        completeInspectionUseCase.completeInspection(command);
        return ResponseEntity.ok().body("Vessel inspection for %s is completed".formatted(vesselNumber));

    }

}
