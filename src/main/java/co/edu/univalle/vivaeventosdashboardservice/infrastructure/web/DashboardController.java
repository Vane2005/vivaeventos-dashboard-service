package co.edu.univalle.vivaeventosdashboardservice.infrastructure.web;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.application.usecase.GetDashboardMetrics;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final GetDashboardMetrics getDashboardMetrics;

    public DashboardController(GetDashboardMetrics getDashboardMetrics) {
        this.getDashboardMetrics = getDashboardMetrics;
    }

    @GetMapping("/metrics")
    public DashboardMetrics metrics() {
        return getDashboardMetrics.execute();
    }
}