package co.edu.univalle.vivaeventosdashboardservice.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardResponse {

    private Long totalTicketsSold;

    private Double totalRevenue;

    private Long validatedTickets;

    private Long pendingTickets;

    private Long abandonedPurchases;

    private String peakSalesHour;
}