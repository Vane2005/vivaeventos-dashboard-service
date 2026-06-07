package co.edu.univalle.vivaeventosdashboardservice.infrastructure.messaging;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record TicketGeneratedMessage(
        UUID orderId,
        BigDecimal amount,
        String currency,
        String customerEmail,
        List<String> qrCodes
) {}