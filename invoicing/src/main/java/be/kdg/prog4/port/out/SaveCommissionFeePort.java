package be.kdg.prog4.port.out;

import be.kdg.prog4.domain.CommissionFee;

public interface SaveCommissionFeePort {
    void save(CommissionFee commissionFee);
}
