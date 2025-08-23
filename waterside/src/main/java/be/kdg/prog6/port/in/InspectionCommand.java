package be.kdg.prog6.port.in;

import java.time.LocalDate;

public record InspectionCommand(String vesselNumber, String inspectorSignature, LocalDate inspectionDate) {
}
