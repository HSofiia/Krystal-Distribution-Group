package be.kdg.prog6.family.adapter.in;

import be.kdg.prog6.family.adapter.in.dto.AppointmentDto;
import be.kdg.prog6.family.adapter.in.dto.AppointmentRequestDto;
import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.in.MakeAppointmentCommand;
import be.kdg.prog6.family.port.in.MakeAppointmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class MakeAppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequestDto appointmentRequestDTO,
                                             @PathVariable UUID customerId) {

        // Create the command with the necessary information
        MakeAppointmentCommand command = new MakeAppointmentCommand(
                appointmentRequestDTO.getScheduleDateTime(),
                new TruckPlate(appointmentRequestDTO.getLicensePlate()),
                MaterialType.valueOf(appointmentRequestDTO.getMaterialType().toUpperCase()),
                new SellerId(customerId)
        );

        // Call the use case to make the appointment
        Appointment appointment = makeAppointmentUseCase.makeAppointment(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to make the appointment"));
        // Convert the domain appointment to a DTO
        AppointmentDto appointmentDTO = new AppointmentDto(
                appointment.getScheduledTime(),
                appointment.getWarehouseNumber(),
                appointment.getMaterialType().name(),
                appointment.getTruck().licensePlate(),
                appointment.getStatus().toString()
        );

        // Return the response with status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO);
    }
}
