package be.kdg.prog6.landside.adapter.in;

import be.kdg.prog6.landside.adapter.in.dto.AppointmentDto;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaRepository;
import be.kdg.prog6.landside.domain.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointmentsList")
public class AppointmentController {

    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentController(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentJpaEntity> appointments = appointmentJpaRepository.findAll();

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(AppointmentDto::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDtos);
    }
}