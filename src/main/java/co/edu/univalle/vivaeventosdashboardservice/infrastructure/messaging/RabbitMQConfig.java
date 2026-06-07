package co.edu.univalle.vivaeventosdashboardservice.infrastructure.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "vivaeventos.events";

    public static final String DASHBOARD_QUEUE = "dashboard-service.ticket.generated";

    public static final String ROUTING_KEY_TICKET_GENERATED = "ticket.generated";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue dashboardQueue() {
        return QueueBuilder.durable(DASHBOARD_QUEUE).build();
    }

    @Bean
    public Binding dashboardBinding(Queue dashboardQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(dashboardQueue)
                .to(exchange)
                .with(ROUTING_KEY_TICKET_GENERATED);
    }
}