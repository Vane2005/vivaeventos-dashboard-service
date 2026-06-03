package co.edu.univalle.vivaeventosdashboardservice.application.usecase;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class GetDashboardMetrics {

    private final DashboardRepository repository;

    public GetDashboardMetrics(DashboardRepository repository) {
        this.repository = repository;
    }

    public DashboardMetrics execute() {
        return repository.getMetrics();
    }
}