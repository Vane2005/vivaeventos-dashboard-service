package co.edu.univalle.vivaeventosdashboardservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DashboardMetricsTest {

    @Test
    void shouldUseConstructorAndGetters() {

        DashboardMetrics metrics = new DashboardMetrics(
                10L,
                50000.0,
                5L,
                2L,
                1L
        );

        assertEquals(10L, metrics.getTotalTicketsSold());
        assertEquals(50000.0, metrics.getTotalRevenue());
        assertEquals(5L, metrics.getValidatedTickets());
    }

    @Test
    void shouldUseSetters() {

        DashboardMetrics metrics = new DashboardMetrics();

        metrics.setTotalTicketsSold(15L);
        metrics.setTotalRevenue(70000.0);

        assertEquals(15L, metrics.getTotalTicketsSold());
        assertEquals(70000.0, metrics.getTotalRevenue());
    }
}