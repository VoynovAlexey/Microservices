package mentorship.roadmap.microservices.service_b.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
public record MessageDto(

        @NotBlank(message = "id is required")
        String id,

        @NotBlank(message = "message is required")
        String message,

        @NotNull(message = "type is required")
        Type type
) {}
