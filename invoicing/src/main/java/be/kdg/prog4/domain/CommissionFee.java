package be.kdg.prog4.domain;

import be.kdg.prog6.common.domain.SellerId;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommissionFee {
    private UUID id;
    private SellerId sellerId;
    private PONumber poNumber;
    private double poAmount;         // baseAmount * (ratePercent/100)
    private boolean invoiced;
    private UUID invoiceId;

    public CommissionFee(UUID id, SellerId sellerId, PONumber poNumber, double poAmount) {
        this.id = id;
        this.sellerId = sellerId;
        this.poNumber = poNumber;
        this.poAmount = poAmount;
    }

    public void markInvoiced(UUID invoiceId) {
        if (invoiceId == null) throw new IllegalArgumentException("invoiceId is required");
        if (this.invoiced && !invoiceId.equals(this.invoiceId)) {
            throw new IllegalStateException("Row already invoiced on " + this.invoiceId);
        }
        this.invoiced = true;
        this.invoiceId = invoiceId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public PONumber getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(PONumber poNumber) {
        this.poNumber = poNumber;
    }

    public double getAmount() {
        return poAmount;
    }

    public void setAmount(double poAmount) {
        this.poAmount = poAmount;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }
}
