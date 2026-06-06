package co.edu.univalle.vivaeventosdashboardservice.application.usecase;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProcessSaleEventTest {

    @Test
    void shouldUpdateExistingMetrics() {

        DashboardRepository repository = mock(DashboardRepository.class);

        DashboardMetrics metrics = new DashboardMetrics(
                5L, 100000.0, 0L, 0L, 0L
        );

        when(repository.getMetrics()).thenReturn(metrics);

        ProcessSaleEvent service = new ProcessSaleEvent(repository);

        service.receiveSaleEvent("sale");

        verify(repository).save(any(DashboardMetrics.class));
    }

    @Test
    void shouldCreateDefaultMetricsWhenRepositoryFails() {

        DashboardRepository repository = mock(DashboardRepository.class);

        when(repository.getMetrics())
                .thenThrow(new RuntimeException());

        ProcessSaleEvent service = new ProcessSaleEvent(repository);

        service.receiveSaleEvent("sale");

        verify(repository).save(any(DashboardMetrics.class));
    }
}