package co.edu.univalle.vivaeventosdashboardservice.infrastructure.persistence;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardRepositoryAdapterTest {

    @Test
    void shouldSaveMetrics() {

        DashboardJpaRepository repository = mock(DashboardJpaRepository.class);

        DashboardEntity entity = new DashboardEntity();
        entity.setTotalTicketsSold(5L);
        entity.setTotalRevenue(1000.0);

        when(repository.save(any())).thenReturn(entity);

        DashboardRepositoryAdapter adapter =
                new DashboardRepositoryAdapter(repository);

        DashboardMetrics metrics = new DashboardMetrics(
                5L,1000.0,0L,0L,0L
        );

        DashboardMetrics result = adapter.save(metrics);

        assertEquals(5L, result.getTotalTicketsSold());
    }

    @Test
    void shouldReturnMetricsWhenDataExists() {

        DashboardJpaRepository repository = mock(DashboardJpaRepository.class);

        DashboardEntity entity = new DashboardEntity();
        entity.setTotalTicketsSold(20L);
        entity.setTotalRevenue(5000.0);

        when(repository.findAll()).thenReturn(List.of(entity));

        DashboardRepositoryAdapter adapter =
                new DashboardRepositoryAdapter(repository);

        DashboardMetrics result = adapter.getMetrics();

        assertEquals(20L, result.getTotalTicketsSold());
    }

    @Test
    void shouldReturnEmptyMetricsWhenNoDataExists() {

        DashboardJpaRepository repository = mock(DashboardJpaRepository.class);

        when(repository.findAll()).thenReturn(List.of());

        DashboardRepositoryAdapter adapter =
                new DashboardRepositoryAdapter(repository);

        DashboardMetrics result = adapter.getMetrics();

        assertNotNull(result);
    }
}