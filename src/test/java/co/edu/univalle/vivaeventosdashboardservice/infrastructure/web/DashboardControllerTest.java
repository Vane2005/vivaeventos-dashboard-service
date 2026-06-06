package co.edu.univalle.vivaeventosdashboardservice.infrastructure.web;

import co.edu.univalle.vivaeventosdashboardservice.application.usecase.GetDashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Test
    void shouldReturnMetrics() {

        GetDashboardMetrics useCase = mock(GetDashboardMetrics.class);

        DashboardMetrics metrics = new DashboardMetrics(
                10L, 100000.0, 1L, 2L, 3L
        );

        when(useCase.execute()).thenReturn(metrics);

        DashboardController controller = new DashboardController(useCase);

        DashboardMetrics result = controller.metrics();

        assertEquals(10L, result.getTotalTicketsSold());
    }
}