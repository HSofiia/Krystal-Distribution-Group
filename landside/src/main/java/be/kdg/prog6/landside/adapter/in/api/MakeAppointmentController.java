package be.kdg.prog6.landside.adapter.in.api;

import be.kdg.prog6.landside.adapter.in.api.dto.AppointmentDto;
import be.kdg.prog6.landside.adapter.in.api.dto.AppointmentRequestDto;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.port.in.MakeAppointmentCommand;
import be.kdg.prog6.landside.port.in.MakeAppointmentUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class MakeAppointmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MakeAppointmentController.class);
    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<AppointmentDto> makeAppointment(@RequestBody AppointmentRequestDto appointmentRequestDTO,
                                                          @PathVariable UUID customerId,
                                                          @AuthenticationPrincipal Jwt jwt) {

        if (jwt != null) {
            LOGGER.info("{} is making appointment", jwt.getClaims().get("email"));
        }

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
