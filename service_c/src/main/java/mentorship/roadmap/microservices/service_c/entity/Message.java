package mentorship.roadmap.microservices.service_c.entity;

import jakarta.persistence.*;
import lombok.Data;
import mentorship.roadmap.microservices.service_c.dto.Type;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
@Entity
@Data
public class Message {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(nullable = false)
    private Type type;

}
