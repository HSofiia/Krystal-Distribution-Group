package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class AppointmentActivityDatabaseAdapterTest {

    @Mock
    private AppointmentActivityJpaRepository appointmentActivityJpaRepository;

    @InjectMocks
    private AppointmentActivityDatabaseAdapter appointmentActivityDatabaseAdapter;

    @Test
    public void testFindByLicencePlateAndStatus() {
        // Set up mock data
        AppointmentActivityJpaEntity mockActivity = new AppointmentActivityJpaEntity();
        mockActivity.setLicencePlate("ABC123");
        mockActivity.setStatus(AppointmentStatus.ARRIVED_ON_TIME);

        // Mock repository behavior
        when(appointmentActivityJpaRepository.findByLicencePlateAndStatus("ABC123", AppointmentStatus.ARRIVED_ON_TIME))
                .thenReturn(Optional.of(mockActivity));

        // Execute the method
        Optional<Activity> result = appointmentActivityDatabaseAdapter.findByLicencePlateAndStatus("ABC123", AppointmentStatus.ARRIVED_ON_TIME);

        // Validate the result
        assertTrue(result.isPresent());
        assertEquals("ABC123", result.get().licencePlate().licensePlate());
        assertEquals(AppointmentStatus.ARRIVED_ON_TIME, result.get().status());

        // Verify the interaction with the repository
        verify(appointmentActivityJpaRepository, times(1))
                .findByLicencePlateAndStatus("ABC123", AppointmentStatus.ARRIVED_ON_TIME);
    }
}