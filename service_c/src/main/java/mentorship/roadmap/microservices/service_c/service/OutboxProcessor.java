package mentorship.roadmap.microservices.service_c.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_c.entity.OutboxMessage;
import mentorship.roadmap.microservices.service_c.repository.OutboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 04.02.2026
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxProcessor {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final int MAX_PROCESSING_MESSAGES = 50;

    @Value("${kafka.topic.out}")
    private String outTopic;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processOutbox() {
        List<OutboxMessage> outboxMessageList = outboxRepository.findByProcessedAtIsNull().stream()
                .limit(MAX_PROCESSING_MESSAGES)
                .toList();

        outboxMessageList.forEach(outboxMessage -> {
            try {
                kafkaTemplate.send(outTopic, outboxMessage.getPayload())
                        .get();

                log.info("C: Message successfully sent using outbox pattern: {}", outboxMessage.getPayload());

                outboxMessage.setProcessedAt(LocalDateTime.now());
                outboxRepository.save(outboxMessage);
            } catch (ExecutionException e) {
                log.error("C: Failed to send message using outbox pattern: {}", outboxMessage.getPayload(), e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("C: Failed to send message using outbox pattern: {}", outboxMessage.getPayload(), e);
            }
        });
    }

}
