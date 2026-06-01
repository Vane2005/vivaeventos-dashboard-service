package co.edu.univalle.vivaeventosdashboardservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetrics {

    private Long totalTicketsSold;
    private Double totalRevenue;
    private Long validatedTickets;
    private Long pendingTickets;
    private Long abandonedPurchases;
}