package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.Schedule;
import be.kdg.prog6.landside.domain.Warehouse;
import be.kdg.prog6.landside.port.in.MakeAppointmentCommand;
import be.kdg.prog6.landside.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.landside.port.out.AppointmentCreatedPort;
import be.kdg.prog6.landside.port.out.LoadSchedulePort;
import be.kdg.prog6.landside.port.out.LoadWarehouseByMaterialTypePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final LoadSchedulePort scheduleDetailsPort;

    private final LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort;

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort,
                                      LoadSchedulePort scheduleDetailsPort, LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort) {
        this.appointmentCreatedPort = appointmentCreatedPort;
        this.scheduleDetailsPort = scheduleDetailsPort;
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
