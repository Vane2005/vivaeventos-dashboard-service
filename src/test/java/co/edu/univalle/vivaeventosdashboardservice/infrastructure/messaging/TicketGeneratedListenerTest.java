package co.edu.univalle.vivaeventosdashboardservice.infrastructure.messaging;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketGeneratedListenerTest {

    private DashboardRepository repository;
    private TicketGeneratedListener listener;

    @BeforeEach
    void setUp() {
        repository = mock(DashboardRepository.class);
        listener = new TicketGeneratedListener(repository);
    }

    @Test
    @DisplayName("Debe actualizar métricas con datos reales del mensaje")
    void shouldUpdateMetricsWithRealData() {
        DashboardMetrics metrics = new DashboardMetrics(10L, 500000.0, 0L, 5L, 0L);
        when(repository.getMetrics()).thenReturn(metrics);

        TicketGeneratedMessage message = new TicketGeneratedMessage(
                UUID.randomUUID(),
                new BigDecimal("100000"),
                "COP",
                "test@test.com",
                List.of("QR1", "QR2")
        );

        listener.receive(message);

        assertThat(metrics.getTotalTicketsSold()).isEqualTo(12L);
        assertThat(metrics.getTotalRevenue()).isEqualTo(600000.0);
        verify(repository).save(any(DashboardMetrics.class));
    }

    @Test
    @DisplayName("Debe inicializar métricas cuando no existen")
    void shouldInitializeMetricsWhenNotFound() {
        when(repository.getMetrics()).thenThrow(new RuntimeException());

        TicketGeneratedMessage message = new TicketGeneratedMessage(
                UUID.randomUUID(),
                new BigDecimal("50000"),
                "COP",
                "test@test.com",
                List.of("QR1")
        );

        listener.receive(message);

        verify(repository).save(any(DashboardMetrics.class));
    }
}