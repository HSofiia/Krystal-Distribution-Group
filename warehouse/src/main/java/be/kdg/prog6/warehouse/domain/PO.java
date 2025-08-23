package be.kdg.prog6.warehouse.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public final class PO {
    private Seller seller;
    private final List<OrderLine> orderLines;
    private final PONumber poNumber;
    private OrderStatus status;
    private final LocalDateTime receivedDateTime;

    public PO(Seller seller, List<OrderLine> orderLines, PONumber poNumber, OrderStatus status, LocalDateTime receivedDateTime) {
        this.seller = seller;
        this.orderLines = orderLines;
        this.poNumber = poNumber;
        this.status = status;
        this.receivedDateTime = receivedDateTime;
    }

    public boolean isNotFilled() {
        return !status.equals(OrderStatus.FILLED);
    }

    public boolean isOutstanding() {
        return status.equals(OrderStatus.OUTSTANDING);
    }

    public void fillOrder() {
        this.status = OrderStatus.FILLED;
    }

    public void matchOrder() {
        this.status = OrderStatus.MATCHED;
    }

    public Seller getSeller() {
        return seller;
    }

    public List<OrderLine> orderLines() {
        return orderLines;
    }

    public PONumber poNumber() {
        return poNumber;
    }

    public OrderStatus status() {
        return status;
    }

    public LocalDateTime getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PO) obj;
        return Objects.equals(this.seller, that.seller) &&
                Objects.equals(this.orderLines, that.orderLines) &&
                Objects.equals(this.poNumber, that.poNumber) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seller, orderLines, poNumber, status);
    }

    @Override
    public String toString() {
        return "PurchaseOrder[" +
                "sellerId=" + seller + ", " +
                "orderLines=" + orderLines + ", " +
                "poNumber=" + poNumber + ", " +
                "status=" + status + ']';
    }


    public enum OrderStatus {
        OUTSTANDING, MATCHED, FILLED
    }
}
