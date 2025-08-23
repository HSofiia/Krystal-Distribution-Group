package be.kdg.prog6.domain;

import java.time.LocalDate;

public class BunkeringOperation {
    private OperationStatus status = OperationStatus.PENDING;
    private LocalDate bunkeringDate;
    public static int MAX_BO_LIMIT = 6;

    public BunkeringOperation(LocalDate bunkeringDate) {
        this.bunkeringDate = bunkeringDate;
    }

    public BunkeringOperation() {
    }

    public OperationStatus status() { return status; }

    public LocalDate getBunkeringDate() {
        return bunkeringDate;
    }

    public void setBunkeringDate(LocalDate bunkeringDate) {
        this.bunkeringDate = bunkeringDate;
    }
}
