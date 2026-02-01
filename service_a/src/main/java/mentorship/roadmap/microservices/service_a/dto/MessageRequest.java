package mentorship.roadmap.microservices.service_a.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 30.01.2026
 */
public record MessageRequest (

        String id,
        String message,
        Type type
) {}
