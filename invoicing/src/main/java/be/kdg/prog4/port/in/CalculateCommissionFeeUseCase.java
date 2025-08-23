package be.kdg.prog4.port.in;


import be.kdg.prog4.event.CommissionEvent;

@FunctionalInterface
public interface CalculateCommissionFeeUseCase {
    void registerCommission(CommissionEvent commissionFeeEvent);
}
