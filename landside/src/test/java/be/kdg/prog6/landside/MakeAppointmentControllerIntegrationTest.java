package be.kdg.prog6.landside;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.landside.adapter.out.DumpPayloadEventPublisher;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaRepository;
import be.kdg.prog6.landside.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class MakeAppointmentControllerIntegrationTest extends AbstractDatabaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    @Test
    @WithMockUser(username = "user@example.com")
    void shouldMakeAppointment() throws Exception {
        // Arrange
        appointmentJpaRepository.save(new AppointmentJpaEntity(
                "ABC123",
                MaterialType.CEMENT,
                4,
                LocalDateTime.of(2024, 10, 11, 10, 20),
                new ScheduleJpaEntity(),
                AppointmentStatus.SCHEDULED
        ));

        // Act
        final ResultActions result = mockMvc.perform(
                post("/appointment/" + TestIds.SELLER_ID.sellerId())
                        .contentType("application/json")
                        .content("{\"scheduleDateTime\": \"2024-10-11T10:20:00\", \"licensePlate\": \"ABC123\", \"materialType\": \"CEMENT\"}")
                        .with(csrf())
        );

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.licencePlate", equalTo("ABC123")))
                .andExpect(jsonPath("$.materialType", equalTo("CEMENT")));
    }
}