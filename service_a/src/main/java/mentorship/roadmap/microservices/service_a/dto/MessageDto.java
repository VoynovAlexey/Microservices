package mentorship.roadmap.microservices.service_a.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
public record MessageDto(

        @NotBlank(message = "message is required")
        String message,

        @NotBlank(message = "type is required")
        Type type
) {}
