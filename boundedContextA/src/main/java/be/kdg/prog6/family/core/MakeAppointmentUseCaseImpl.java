package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.Schedule;
import be.kdg.prog6.family.domain.Warehouse;
import be.kdg.prog6.family.port.in.MakeAppointmentCommand;
import be.kdg.prog6.family.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.family.port.out.AppointmentCreatedPort;
import be.kdg.prog6.family.port.out.LoadSchedulePort;
import be.kdg.prog6.family.port.out.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.family.port.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final LoadSchedulePort scheduleDetailsPort;
    private final LoadWarehousePort warehousePort;
    private final LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort;

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort,
                                      LoadSchedulePort scheduleDetailsPort,
                                      LoadWarehousePort warehousePort, LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort) {
        this.appointmentCreatedPort = appointmentCreatedPort;
        this.scheduleDetailsPort = scheduleDetailsPort;
        this.warehousePort = warehousePort;
        this.warehouseByMaterialTypePort = warehouseByMaterialTypePort;
    }

    @Override
    public Optional<Appointment> makeAppointment(MakeAppointmentCommand createAppointmentCommand) {
        // 1. Get warehouse information using the WarehousePort
        Warehouse warehouse = warehouseByMaterialTypePort.getWarehouse(
                createAppointmentCommand.materialType());

        Schedule schedule = scheduleDetailsPort.loadScheduleByDate(createAppointmentCommand.scheduledTime());

        // 3. Schedule the appointment in the loaded schedule
        Optional<Appointment> appointment = schedule.scheduleAppointment(
                createAppointmentCommand.truckLicensePlate(),
                createAppointmentCommand.materialType(),
                warehouse.warehouseId(),
                warehouse.warehouseNumber());

        // 4. Check if the warehouse is full or appointment could not be scheduled
        if (!warehouse.isEnoughSpace() || appointment.isEmpty()) {
            return Optional.empty();
        }else {

            // 5. Save the new appointment using the AppointmentCreatedPort
            Appointment newAppointment = appointment.get();
            appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());

            // 6. Return the newly created appointment
            return Optional.of(newAppointment);
        }
    }
}
