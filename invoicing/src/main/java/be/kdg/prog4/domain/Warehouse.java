package be.kdg.prog4.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Warehouse {
    private final int warehouseNumber;
    private final SellerId sellerId;
    private final MaterialType materialType;
    private final List<Payload> payloads;
    private final double COMMISION_FEE = 0.01;

    public Warehouse(int warehouseNumber, SellerId sellerId, MaterialType materialType, List<Payload> payloads) {
        this.warehouseNumber = warehouseNumber;
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.payloads = (payloads instanceof java.util.ArrayList)
                ? payloads
                : new java.util.ArrayList<>(payloads);
    }

    public void removeOldPayloadsFirst(double tonsToRemove) {
        payloads.sort(Comparator.comparing(Payload::getDeliveredAt));
        double removed = 0.0;

        Iterator<Payload> it = payloads.iterator();
        while (it.hasNext() && tonsToRemove > 0) {
            Payload p = it.next();
            double available = p.getNetTons();

            if (available <= tonsToRemove) {
                tonsToRemove -= available;
                removed += available;
                it.remove();
            } else {
                removed += tonsToRemove;
                payloads.set(payloads.indexOf(p), new Payload(p.getNetTons() - tonsToRemove, p.getDeliveredAt()));
                tonsToRemove = 0.0;
            }
        }
    }

    public double calculateCommissionFee(double tons) {
        return materialType.calculatePricePerTon(tons) * COMMISION_FEE;
    }

    public double calculateStorageFee(LocalDate asOfDate) {
        return payloads.stream()
                .mapToDouble(p -> {
                    long days = ChronoUnit.DAYS.between(p.getDeliveredAt(), asOfDate.atStartOfDay());
                    if (days < 0) days = 0;

                    return materialType.calculatePricePerTonPerDay(p.getNetTons(), days);
                })
                .sum();
    }

    public void addPayload(double tons, LocalDateTime deliveredAt) {
        payloads.add(new Payload(tons, deliveredAt));
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }
}
