package co.edu.univalle.vivaeventosdashboardservice.infrastructure.security;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;

import static org.junit.jupiter.api.Assertions.*;

class RabbitMQConfigTest {

    @Test
    void shouldCreateQueue() {

        RabbitMQConfig config = new RabbitMQConfig();

        Queue queue = config.queue();

        assertEquals("sales.queue", queue.getName());
    }
}