package co.edu.univalle.vivaeventosdashboardservice.infrastructure.messaging;

import co.edu.univalle.vivaeventosdashboardservice.domain.model.DashboardMetrics;
import co.edu.univalle.vivaeventosdashboardservice.domain.port.DashboardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TicketGeneratedListener {

    private final DashboardRepository repository;

    public TicketGeneratedListener(DashboardRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.DASHBOARD_QUEUE)
    public void receive(TicketGeneratedMessage message) {
        DashboardMetrics metrics = getOrInitMetrics();

        // Actualizar con datos reales del mensaje
        int ticketsInOrder = message.qrCodes().size();
        metrics.setTotalTicketsSold(metrics.getTotalTicketsSold() + ticketsInOrder);
        metrics.setTotalRevenue(
                metrics.getTotalRevenue() + message.amount().doubleValue()
        );
        metrics.setPendingTickets(
                Math.max(0, metrics.getPendingTickets() - ticketsInOrder)
        );

        repository.save(metrics);
    }

    private DashboardMetrics getOrInitMetrics() {
        try {
            DashboardMetrics metrics = repository.getMetrics();
            if (metrics.getTotalTicketsSold() == null) return initMetrics();
            return metrics;
        } catch (Exception e) {
            return initMetrics();
        }
    }

    private DashboardMetrics initMetrics() {
        DashboardMetrics metrics = new DashboardMetrics();
        metrics.setTotalTicketsSold(0L);
        metrics.setTotalRevenue(0.0);
        metrics.setValidatedTickets(0L);
        metrics.setPendingTickets(0L);
        metrics.setAbandonedPurchases(0L);
        return metrics;
    }
}