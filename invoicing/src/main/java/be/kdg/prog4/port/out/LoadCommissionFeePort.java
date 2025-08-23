package be.kdg.prog4.port.out;

import be.kdg.prog4.domain.CommissionFee;
import be.kdg.prog4.domain.PONumber;

import java.util.Optional;

public interface LoadCommissionFeePort {
    Optional<CommissionFee> loadByPoNumber(PONumber poNumber);
}
