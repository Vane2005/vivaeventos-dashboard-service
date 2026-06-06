package co.edu.univalle.vivaeventosdashboardservice.infrastructure.security;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaleEventListenerTest {

    @Test
    void shouldProcessSaleEvent() {

        DashboardRepository repository = mock(DashboardRepository.class);

        DashboardMetrics metrics = new DashboardMetrics(
                1L, 1000.0, 0L, 0L, 0L
        );

        when(repository.getMetrics()).thenReturn(metrics);

        SaleEventListener listener = new SaleEventListener(repository);

        listener.receiveSaleEvent("sale");

        verify(repository).save(any(DashboardMetrics.class));
    }

    @Test
    void shouldHandleRepositoryException() {

        DashboardRepository repository = mock(DashboardRepository.class);

        when(repository.getMetrics())
                .thenThrow(new RuntimeException());

        SaleEventListener listener = new SaleEventListener(repository);

        listener.receiveSaleEvent("sale");

        verify(repository).save(any(DashboardMetrics.class));
    }
}