package mentorship.roadmap.microservices.service_c.service;

import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_c.entity.OutboxMessage;
import mentorship.roadmap.microservices.service_c.repository.OutboxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 08.03.2026
 */
@Service
@RequiredArgsConstructor
public class OutboxMessageSaver {

    private final OutboxRepository outboxRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markAsProcessed(OutboxMessage outboxMessage) {
        outboxMessage.setProcessedAt(LocalDateTime.now());
        outboxRepository.save(outboxMessage);
    }

}
