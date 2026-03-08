package mentorship.roadmap.microservices.service_b.dto;

import java.util.Map;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 08.03.2026
 */
public record ErrorDto(
        String message,
        Map<String, String> fields
) {}
