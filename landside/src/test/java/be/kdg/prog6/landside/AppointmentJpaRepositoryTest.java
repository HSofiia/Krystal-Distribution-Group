package be.kdg.prog6.landside;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaRepository;
import be.kdg.prog6.landside.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AppointmentJpaRepositoryTest {

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    @Autowired
    private org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager tem;

    private ScheduleJpaEntity persistSchedule() {
        // Minimal schedule to satisfy the NOT NULL FK (scheduleId)
        ScheduleJpaEntity schedule = new ScheduleJpaEntity();
        // assuming ScheduleJpaEntity has setId; if it generates IDs itself you can omit this line
        schedule.setId(UUID.randomUUID());
        return tem.persistAndFlush(schedule);
    }

    private AppointmentJpaEntity persistAppointment(
            String license,
            AppointmentStatus status,
            LocalDateTime scheduledTime
    ) {
        ScheduleJpaEntity schedule = persistSchedule();

        AppointmentJpaEntity a = new AppointmentJpaEntity(
                license,
                MaterialType.CEMENT,
                /* warehouseNumber */ 1,
                scheduledTime,
                schedule,
                status
        );
        // If you prefer setting an explicit UUID:
        a.setId(UUID.randomUUID());

        return tem.persistAndFlush(a);
    }

    @Test
    @DisplayName("findLatestAppointmentByLicensePlate returns the most recent by scheduledTime")
    void findLatestAppointmentByLicensePlate_returns_most_recent() {
        // Arrange
        String plate = "X-999-111";
        persistAppointment(plate, AppointmentStatus.SCHEDULED, LocalDateTime.now().minusHours(2));
        AppointmentJpaEntity latest = persistAppointment(plate, AppointmentStatus.SCHEDULED, LocalDateTime.now().minusMinutes(10));
        persistAppointment("OTHER-123", AppointmentStatus.SCHEDULED, LocalDateTime.now().minusMinutes(5));

        // Act
        Optional<AppointmentJpaEntity> found =
                appointmentJpaRepository.findLatestAppointmentByLicensePlate(plate);

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(latest.getId());
        assertThat(found.get().getLicensePlate()).isEqualTo(plate);
    }

    @Test
    @DisplayName("findByLicensePlateAndStatusFetched returns the appointment for plate + status")
    void findByLicensePlateAndStatusFetched_returns_matching() {
        // Arrange
        String plate = "TRK-2025";
        persistAppointment(plate, AppointmentStatus.SCHEDULED, LocalDateTime.now().plusHours(1));
        AppointmentJpaEntity checkedIn = persistAppointment(plate, AppointmentStatus.ARRIVED_ON_TIME, LocalDateTime.now().plusHours(2));
        persistAppointment("NOPE-000", AppointmentStatus.SCHEDULED, LocalDateTime.now());

        // Act
        Optional<AppointmentJpaEntity> found =
                appointmentJpaRepository.findByLicensePlateAndStatusFetched(plate, AppointmentStatus.ARRIVED_ON_TIME);

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(checkedIn.getId());
        assertThat(found.get().getStatus()).isEqualTo(AppointmentStatus.ARRIVED_ON_TIME);
        assertThat(found.get().getLicensePlate()).isEqualTo(plate);
    }
}
