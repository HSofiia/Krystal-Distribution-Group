package be.kdg.prog6.landside.adapter.in;

import be.kdg.prog6.landside.adapter.in.dto.AppointmentDto;
import be.kdg.prog6.landside.adapter.in.dto.AppointmentRequestDto;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.landside.domain.TruckPlate;
import be.kdg.prog6.landside.port.in.MakeAppointmentCommand;
import be.kdg.prog6.landside.port.in.MakeAppointmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class MakeAppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<AppointmentDto> makeAppointment(@RequestBody AppointmentRequestDto appointmentRequestDTO,
                                             @PathVariable UUID customerId) {

        MakeAppointmentCommand command = new MakeAppointmentCommand(
                appointmentRequestDTO.getScheduleDateTime(),
                new TruckPlate(appointmentRequestDTO.getLicensePlate()),
                MaterialType.valueOf(appointmentRequestDTO.getMaterialType().toUpperCase()),
                new SellerId(customerId)
        );

        Appointment appointment = makeAppointmentUseCase.makeAppointment(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to make the appointment"));

        if (appointmentRequestDTO.getLicensePlate() == null || appointmentRequestDTO.getLicensePlate().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "License plate is required");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(AppointmentDto.of(appointment));
    }
}
