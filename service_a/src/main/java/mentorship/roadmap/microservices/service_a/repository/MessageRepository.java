package mentorship.roadmap.microservices.service_a.repository;

import mentorship.roadmap.microservices.service_a.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
