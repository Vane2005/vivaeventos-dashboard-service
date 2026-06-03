package co.edu.univalle.vivaeventosdashboardservice.infrastructure.persistence;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardRepositoryAdapter implements DashboardRepository {

    private final DashboardJpaRepository repository;

    public DashboardRepositoryAdapter(DashboardJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public DashboardMetrics save(DashboardMetrics metrics) {

        DashboardEntity entity = new DashboardEntity();

        entity.setTotalTicketsSold(metrics.getTotalTicketsSold());
        entity.setTotalRevenue(metrics.getTotalRevenue());
        entity.setValidatedTickets(metrics.getValidatedTickets());
        entity.setPendingTickets(metrics.getPendingTickets());
        entity.setAbandonedPurchases(metrics.getAbandonedPurchases());

        DashboardEntity saved = repository.save(entity);

        DashboardMetrics result = new DashboardMetrics();

        result.setTotalTicketsSold(saved.getTotalTicketsSold());
        result.setTotalRevenue(saved.getTotalRevenue());
        result.setValidatedTickets(saved.getValidatedTickets());
        result.setPendingTickets(saved.getPendingTickets());
        result.setAbandonedPurchases(saved.getAbandonedPurchases());

        return result;
    }

    @Override
    public DashboardMetrics getMetrics() {

        List<DashboardEntity> dashboards = repository.findAll();

        if (dashboards.isEmpty()) {
            return new DashboardMetrics();
        }

        DashboardEntity entity = dashboards.get(0);

        DashboardMetrics metrics = new DashboardMetrics();

        metrics.setTotalTicketsSold(entity.getTotalTicketsSold());
        metrics.setTotalRevenue(entity.getTotalRevenue());
        metrics.setValidatedTickets(entity.getValidatedTickets());
        metrics.setPendingTickets(entity.getPendingTickets());
        metrics.setAbandonedPurchases(entity.getAbandonedPurchases());

        return metrics;
    }
}