package be.kdg.prog4.domain;

import java.time.LocalDateTime;

public class Payload {
    private double netTons;
    private LocalDateTime deliveredAt;

    public Payload(double netTons, LocalDateTime deliveredAt) {
        if (netTons < 0) throw new IllegalArgumentException("tons < 0");
        this.netTons = netTons;
        this.deliveredAt = deliveredAt;
    }

    public double getNetTons() {
        return netTons;
    }

    public void setNetTons(double netTons) {
        this.netTons = netTons;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
