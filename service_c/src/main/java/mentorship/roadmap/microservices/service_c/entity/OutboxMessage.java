package mentorship.roadmap.microservices.service_c.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 04.02.2026
 */
@Entity
@Table(name = "outbox_messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String payload;

}
