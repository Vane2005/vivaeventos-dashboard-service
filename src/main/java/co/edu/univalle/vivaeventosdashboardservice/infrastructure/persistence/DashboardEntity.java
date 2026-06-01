package co.edu.univalle.vivaeventosdashboardservice.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dashboard_metrics")
public class DashboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long totalTicketsSold;
    private Double totalRevenue;
    private Long validatedTickets;
    private Long pendingTickets;
    private Long abandonedPurchases;
}