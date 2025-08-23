package be.kdg.prog6.port.in;

import java.time.LocalDate;

public interface PlanBunkeringOperationUseCase {
    void planBO(String vesselNumber, LocalDate date);
}
