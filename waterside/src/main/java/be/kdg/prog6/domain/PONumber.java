package be.kdg.prog6.domain;

public record PONumber(String number) {
    public PONumber {
        if (!number.startsWith("PO")) {
            throw new IllegalArgumentException("PONumber must start with 'PO'");
        }
    }
}
