package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.MakeAppointmentCommand;
import be.kdg.prog6.landside.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.landside.port.out.AppointmentCreatedPort;
import be.kdg.prog6.landside.port.out.LoadSchedulePort;
import be.kdg.prog6.landside.port.out.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.landside.port.out.UpdatedAppointmentPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final LoadSchedulePort scheduleDetailsPort;
    private final LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort;
    private final List<UpdatedAppointmentPort> updatedAppointments;

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort,
                                      LoadSchedulePort scheduleDetailsPort, LoadWarehouseByMaterialTypePort warehouseByMaterialTypePort, List<UpdatedAppointmentPort> updatedAppointments) {
        this.appointmentCreatedPort = appointmentCreatedPort;
        this.scheduleDetailsPort = scheduleDetailsPort;
        this.warehouseByMaterialTypePort = warehouseByMaterialTypePort;
        this.updatedAppointments = updatedAppointments;
    }

    @Override
    public Optional<Appointment> makeAppointment(MakeAppointmentCommand createAppointmentCommand) {
        // 1. Get warehouse information using the WarehousePort
        Warehouse warehouse = warehouseByMaterialTypePort.getWarehouse(
                createAppointmentCommand.materialType());

        Schedule schedule = scheduleDetailsPort.loadScheduleByDate(createAppointmentCommand.scheduledTime());

        if (!warehouse.isEnoughSpace()) {
            return Optional.empty();
        }else {
            Optional<Appointment> appointment = schedule.scheduleAppointment(
                    createAppointmentCommand.truckLicensePlate(),
                    createAppointmentCommand.materialType(),
                    warehouse.warehouseId(),
                    warehouse.warehouseNumber(),
                    createAppointmentCommand.scheduledTime());



            Appointment newAppointment = appointment.get();
            appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());

//             6. Create a new activity for this appointment
            Activity newActivity = new Activity(
                    new ActivityId(newAppointment.getId(), UUID.randomUUID()),
                    ActivityType.SCHEDULED,
                    createAppointmentCommand.scheduledTime(),
                    AppointmentStatus.SCHEDULED,
                    warehouse.warehouseId(),
                    createAppointmentCommand.truckLicensePlate()
            );

            // 7. Notify all the updatedAppointment ports with the new appointment and activity
            updatedAppointments.forEach(port -> {
                port.activityCreated(newAppointment, newActivity);
            });

            return Optional.of(newAppointment);
        }
    }
}
