package be.kdg.prog6.landside.core;

import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.in.MakeAppointmentCommand;
import be.kdg.prog6.landside.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.landside.port.out.appointment.AppointmentCreatedPort;
import be.kdg.prog6.landside.port.out.LoadSchedulePort;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehouseByMaterialTypePort;
import be.kdg.prog6.landside.port.out.appointment.UpdatedAppointmentPort;
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
        WarehouseProjector warehouse = warehouseByMaterialTypePort.getWarehouse(
                createAppointmentCommand.materialType());

        Schedule schedule = scheduleDetailsPort.loadScheduleByDate(createAppointmentCommand.scheduledTime());

        if (!warehouse.isEnoughSpace()) {
            return Optional.empty();
        } else {
            Optional<Appointment> appointment = schedule.scheduleAppointment(
                    createAppointmentCommand.truckLicensePlate(),
                    createAppointmentCommand.materialType(),
                    warehouse.getWarehouseId(),
                    warehouse.getWarehouseNumber(),
                    createAppointmentCommand.scheduledTime());

            if (appointment.isEmpty()) return Optional.empty();

            Appointment newAppointment = appointment.get();

            Activity newActivity = newAppointment.addActivity(
                    ActivityType.SCHEDULED,
                    TruckStatus.SCHEDULED,
                    createAppointmentCommand.scheduledTime()
            );

            appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());

            updatedAppointments.forEach(port -> {
                port.activityCreated(newAppointment, newActivity);
            });

            return Optional.of(newAppointment);
        }
    }
}
