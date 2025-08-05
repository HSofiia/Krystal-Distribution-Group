package be.kdg.prog6.landside.adapter.in.api;

import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/site")
public class SiteController {
    private final AppointmentActivityJpaRepository appointmentActivityJpaRepository;

    public SiteController(AppointmentActivityJpaRepository appointmentActivityJpaRepository) {
        this.appointmentActivityJpaRepository = appointmentActivityJpaRepository;
    }

    @GetMapping("/currentTruckCount")
    public ResponseEntity<List<String>> getCurrentTruckCount() {
        List<String> licencePlates = appointmentActivityJpaRepository
                .findAllByActivityTypeOnSite()
                .stream()
                .map(AppointmentActivityJpaEntity::getLicencePlate)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(licencePlates);
    }

}
