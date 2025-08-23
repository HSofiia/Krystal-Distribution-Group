package be.kdg.prog6.domain;

import java.time.LocalDate;
import java.util.UUID;


public class ShipmentOrder {
    private PONumber poNumber;
    private InspectionOperation inspectionOperation;
    private BunkeringOperation bunkeringOperation;

    private String customerEnterpriseNumber;
    private String vesselNumber;

    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Boolean isMatchedWithPO;
    private OperationStatus operationStatus;

    private final InspectionOperation inspection = new InspectionOperation();
    private final BunkeringOperation  bunkering  = new BunkeringOperation();

    public ShipmentOrder(PONumber poNumber, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, InspectionOperation inspectionOperation, BunkeringOperation bunkeringOperation, Boolean isMatchedWithPO, OperationStatus operationStatus) {
        this.poNumber = poNumber;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.isMatchedWithPO = isMatchedWithPO;
        this.operationStatus = operationStatus;
    }

    public ShipmentOrder(PONumber poNumber, String customerEnterpriseNumber, String vesselNumber) {
        this.poNumber = poNumber;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
    }

    public void matchPurchaseOrder() {
        this.isMatchedWithPO = true;
        updateShipmentStatus();
    }

    public boolean readyToDepart() {
        return inspection.status() == OperationStatus.COMPLETED
                && bunkering.status()  == OperationStatus.COMPLETED;
    }

    public void depart() {
        this.departureDate = LocalDate.now();
        this.operationStatus = OperationStatus.COMPLETED;
    }

    private void updateShipmentStatus() {
        if (readyToDepart() && this.isMatchedWithPO) {
            this.operationStatus = OperationStatus.COMPLETED;
        }
    }


    public void scheduleBO(LocalDate date) {
        this.bunkeringOperation.setBunkeringDate(date);
        updateShipmentStatus();
    }

    public void completeIO(LocalDate date, String signature) {
        this.inspectionOperation.setCompletedAt(date);
        this.inspectionOperation.setInspectorSignature(signature);
        this.inspectionOperation.setStatus(OperationStatus.COMPLETED);
        updateShipmentStatus();
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Boolean getMatchedWithPO() {
        return isMatchedWithPO;
    }

    public void setMatchedWithPO(Boolean matchedWithPO) {
        isMatchedWithPO = matchedWithPO;
    }

    public BunkeringOperation getBunkeringOperation() {
        return bunkeringOperation;
    }

    public void setBunkeringOperation(BunkeringOperation bunkeringOperation) {
        this.bunkeringOperation = bunkeringOperation;
    }

    public PONumber getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(PONumber poNumber) {
        this.poNumber = poNumber;
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

    public InspectionOperation getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(InspectionOperation inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }
}
