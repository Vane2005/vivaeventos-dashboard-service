package co.edu.univalle.vivaeventosdashboardservice.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DashboardResponseTest {

    @Test
    void shouldSetAndGetValues() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalTicketsSold(100L);
        response.setTotalRevenue(200000.0);
        response.setValidatedTickets(80L);
        response.setPendingTickets(10L);
        response.setAbandonedPurchases(5L);
        response.setPeakSalesHour("20:00");

        assertEquals(100L, response.getTotalTicketsSold());
        assertEquals(200000.0, response.getTotalRevenue());
        assertEquals("20:00", response.getPeakSalesHour());
    }
}