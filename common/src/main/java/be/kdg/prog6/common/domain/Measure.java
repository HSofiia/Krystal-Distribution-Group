package be.kdg.prog6.common.domain;

public enum Measure {
    KT(1000.0, "kT");

    private final double toTonsFactor;
    private final String code;

    Measure(double toTonsFactor, String code) {
        this.toTonsFactor = toTonsFactor;
        this.code = code;
    }

    public double toTonsFactor() {
        return toTonsFactor;
    }
    public String getCode() {return code;}

    public static Measure fromCode(String code) {
        for (Measure u : values()) if (u.code.equalsIgnoreCase(code)) return u;
        throw new IllegalArgumentException("Unknown MeasureType: " + code);
    }
}
