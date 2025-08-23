package be.kdg.prog6.domain;

import java.time.LocalDate;

public class InspectionOperation {
    private String inspectorSignature;
    private LocalDate completedAt;
    private OperationStatus status = OperationStatus.PENDING;

    public InspectionOperation(String inspectorSignature, LocalDate completedAt) {
        this.inspectorSignature = inspectorSignature;
        this.completedAt = completedAt;
    }

    public InspectionOperation() {
    }

    public OperationStatus status() { return status; }

    public String getInspectorSignature() {
        return inspectorSignature;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDate completedAt) {
        this.completedAt = completedAt;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}
