package be.kdg.prog6.adapter.out.shipmentOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
@Entity
@Table(name = "shipment_orders", catalog = "waterside")
public class ShipmentOrderJpaEntity {

    @Id
    private String poReferenceNumber;

    @Column(nullable = false)
    private String customerEnterpriseNumber;

    @Column(nullable = false)
    private String vesselNumber;

    @Column(nullable = false)
    private LocalDate arrivalDate;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalDate bunkeringOperationDate;

    @Column
    private LocalDate inspectionOperationDate;

    @Column
    private String inspectorSignature;

    @Column(nullable = false)
    private Boolean isMatchedWithPO;

    @Column(nullable = false)
    private String shipmentStatus;



    public ShipmentOrderJpaEntity() {
    }

    public ShipmentOrderJpaEntity(String poReferenceNumber, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, LocalDate bunkeringOperationDate, LocalDate inspectionOperationDate, String inspectorSignature, Boolean isMatchedWithPO, String shipmentStatus) {
        this.poReferenceNumber = poReferenceNumber;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.bunkeringOperationDate = bunkeringOperationDate;
        this.inspectionOperationDate = inspectionOperationDate;
        this.inspectorSignature = inspectorSignature;
        this.isMatchedWithPO = isMatchedWithPO;
        this.shipmentStatus = shipmentStatus;
    }

    public String getPoReferenceNumber() {
        return poReferenceNumber;
    }

    public void setPoReferenceNumber(String poReferenceNumber) {
        this.poReferenceNumber = poReferenceNumber;
    }

    public String getCustomerEnterpriseNumber() {
        return customerEnterpriseNumber;
    }

    public void setCustomerEnterpriseNumber(String customerEnterpriseNumber) {
        this.customerEnterpriseNumber = customerEnterpriseNumber;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getBunkeringOperationDate() {
        return bunkeringOperationDate;
    }

    public void setBunkeringOperationDate(LocalDate bunkeringOperationDate) {
        this.bunkeringOperationDate = bunkeringOperationDate;
    }

    public LocalDate getInspectionOperationDate() {
        return inspectionOperationDate;
    }

    public void setInspectionOperationDate(LocalDate inspectionOperationDate) {
        this.inspectionOperationDate = inspectionOperationDate;
    }

    public String getInspectorSignature() {
        return inspectorSignature;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }

    public Boolean getMatchedWithPO() {
        return isMatchedWithPO;
    }

    public void setMatchedWithPO(Boolean matchedWithPO) {
        isMatchedWithPO = matchedWithPO;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
