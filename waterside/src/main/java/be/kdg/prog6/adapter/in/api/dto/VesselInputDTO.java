package be.kdg.prog6.adapter.in.api.dto;

import java.time.LocalDate;

public class VesselInputDTO {
    private String purchaseOrderNumber;
    private String customerEnterpriseNumber;
    private LocalDate departureDate;

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getCustomerEnterpriseNumber() {
        return customerEnterpriseNumber;
    }

    public void setCustomerEnterpriseNumber(String customerEnterpriseNumber) {
        this.customerEnterpriseNumber = customerEnterpriseNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
