package mentorship.roadmap.microservices.service_c.repository;

import mentorship.roadmap.microservices.service_c.entity.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 04.02.2026
 */
@Repository
public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {

    List<OutboxMessage> findByProcessedAtIsNull();

}
