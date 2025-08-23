package be.kdg.prog4.domain;

public record PONumber(String value) {
    public PONumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PO number is required");
        }
    }
    @Override
    public String toString() { return value; }
}