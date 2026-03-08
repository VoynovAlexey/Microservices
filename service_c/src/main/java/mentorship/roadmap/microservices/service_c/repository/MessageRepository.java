package mentorship.roadmap.microservices.service_c.repository;

import mentorship.roadmap.microservices.service_c.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
public interface MessageRepository extends JpaRepository<Message, String> {
}
