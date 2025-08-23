package be.kdg.prog4.adapter.in.api;

import be.kdg.prog4.port.in.CalculateStorageFeeUseCase;
import be.kdg.prog6.common.domain.SellerId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/commissions")
public class CommissionController {
    private final CalculateStorageFeeUseCase calculateStorageFeeUseCase;

    public CommissionController(CalculateStorageFeeUseCase calculateStorageFeeUseCase) {
        this.calculateStorageFeeUseCase = calculateStorageFeeUseCase;
    }

    @GetMapping("/{sellerId}/{asOfDate}")
    public ResponseEntity<?> calculateStorageCosts(@PathVariable UUID sellerId, @PathVariable LocalDate asOfDate) {
        Double amount = calculateStorageFeeUseCase.calculate(new SellerId(sellerId), asOfDate);
        return ResponseEntity.ok(amount);
    }
}
