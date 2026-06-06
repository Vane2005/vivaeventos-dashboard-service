package co.edu.univalle.vivaeventosdashboardservice.application.usecase;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetDashboardMetricsTest {

    @Test
    void shouldReturnMetricsFromRepository() {

        DashboardRepository repository = mock(DashboardRepository.class);

        DashboardMetrics metrics = new DashboardMetrics(
                10L, 50000.0, 2L, 3L, 1L
        );

        when(repository.getMetrics()).thenReturn(metrics);

        GetDashboardMetrics useCase = new GetDashboardMetrics(repository);

        DashboardMetrics result = useCase.execute();

        assertEquals(10L, result.getTotalTicketsSold());
        assertEquals(50000.0, result.getTotalRevenue());
    }
}