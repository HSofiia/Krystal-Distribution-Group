package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.*;
import be.kdg.prog6.family.port.in.MakeAppointmentCommand;
import be.kdg.prog6.family.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.family.port.out.LoadMaterialPort;
import be.kdg.prog6.family.port.out.LoadTruckPort;
import be.kdg.prog6.family.port.out.LoadWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final LoadTruckPort loadTruckPort;
    private final LoadMaterialPort loadMaterialPort;
    private final LoadWarehousePort loadWarehousePort;

    public MakeAppointmentUseCaseImpl(LoadTruckPort loadTruckPort, LoadMaterialPort loadMaterialPort, LoadWarehousePort loadWarehousePort) {
        this.loadTruckPort = loadTruckPort;
        this.loadMaterialPort = loadMaterialPort;
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public Appointment makeAppointment(MakeAppointmentCommand command) {
        Truck truck = loadTruckPort.loadTruckByLicensePlate(command.truckLicensePlate())
                .orElseThrow(() -> new IllegalArgumentException("Truck not found"));

        // Load material details
        Material material = loadMaterialPort.loadMaterialByName(command.materialName())
                .orElseThrow(() -> new IllegalArgumentException("Material not found"));

        // Load warehouse details
        Warehouse warehouse = loadWarehousePort.loadWarehouseById(command.warehouseId())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));

        // Check if warehouse is below 80% capacity
        if (!warehouse.isBelowEightyPercent()) {
            throw new IllegalStateException("Warehouse is above 80% capacity, cannot make an appointment.");
        }

        // Create and save the appointment
        String weighbridgeNumber = UUID.randomUUID().toString();
        Appointment appointment = new Appointment(
                UUID.randomUUID().toString(),
                command.scheduledTime(),
                truck,
                material,
                weighbridgeNumber,
                warehouse.getWarehouseId(),
                AppointmentStatus.SCHEDULED
        );

        saveAppointmentPort.save(appointment);

        return appointment;
    }
}