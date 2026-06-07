package co.edu.univalle.vivaeventosdashboardservice.infrastructure.messaging;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import static org.assertj.core.api.Assertions.assertThat;

class RabbitMQConfigTest {

    @Test
    void shouldCreateExchange() {
        RabbitMQConfig config = new RabbitMQConfig();
        TopicExchange exchange = config.exchange();
        assertThat(exchange.getName()).isEqualTo(RabbitMQConfig.EXCHANGE);
    }

    @Test
    void shouldCreateDashboardQueue() {
        RabbitMQConfig config = new RabbitMQConfig();
        Queue queue = config.dashboardQueue();
        assertThat(queue.getName()).isEqualTo(RabbitMQConfig.DASHBOARD_QUEUE);
        assertThat(queue.isDurable()).isTrue();
    }
}