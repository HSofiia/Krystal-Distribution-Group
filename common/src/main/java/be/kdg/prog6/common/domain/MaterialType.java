package be.kdg.prog6.common.domain;

public enum MaterialType {
    GYPSUM(13.0, 1.0),
    IRON_ORE(110.0, 5.0),
    CEMENT(95.0, 3.0),
    PET_COKE(210.0, 10.0),
    SLAG(160.0, 7.0);

    private final double pricePerTon;
    private final double pricePerTonPerDay;

    MaterialType(double pricePerTon, double pricePerTonPerDay) {
        this.pricePerTon = pricePerTon;
        this.pricePerTonPerDay = pricePerTonPerDay;
    }

    public double getPricePerTonPerDay() {
        return pricePerTonPerDay;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }

    public double calculatePricePerTonPerDay(double tons, long storageDays) {
        return tons * pricePerTonPerDay * storageDays;
    }

    public double calculatePricePerTon(double tons) {
        return tons * pricePerTon;
    }

    public static MaterialType fromCode(String code) {
        return MaterialType.valueOf(code.toUpperCase());
    }
}
