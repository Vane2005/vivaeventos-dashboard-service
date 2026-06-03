package co.edu.univalle.vivaeventosdashboardservice.application.usecase;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import co.edu.univalle.vivaeventosdashboardservice.infrastructure.security.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessSaleEvent{
    private final DashboardRepository repository;

    public ProcessSaleEvent(DashboardRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveSaleEvent(String message) {

        DashboardMetrics metrics;

        try {
            metrics = repository.getMetrics();
        } catch (Exception e) {

            metrics = new DashboardMetrics();

            metrics.setTotalTicketsSold(0L);
            metrics.setTotalRevenue(0.0);
            metrics.setValidatedTickets(0L);
            metrics.setPendingTickets(0L);
            metrics.setAbandonedPurchases(0L);
        }

        metrics.setTotalTicketsSold(
                metrics.getTotalTicketsSold() + 1
        );

        metrics.setTotalRevenue(
                metrics.getTotalRevenue() + 50000
        );

        repository.save(metrics);
    }
}