package co.edu.univalle.vivaeventosdashboardservice.domain.port;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;

public interface DashboardRepository {

    DashboardMetrics save(DashboardMetrics metrics);

    DashboardMetrics getMetrics();
}