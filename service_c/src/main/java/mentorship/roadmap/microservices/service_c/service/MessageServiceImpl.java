package mentorship.roadmap.microservices.service_c.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_c.dto.MessageDto;
import mentorship.roadmap.microservices.service_c.entity.Message;
import mentorship.roadmap.microservices.service_c.entity.OutboxMessage;
import mentorship.roadmap.microservices.service_c.mapper.MessageMapper;
import mentorship.roadmap.microservices.service_c.repository.MessageRepository;
import mentorship.roadmap.microservices.service_c.repository.OutboxRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    private final MessageMapper mapper;


    @Override
    public MessageDto getMessage(String id) {
        return mapper.toMessageDto(
                messageRepository.findById(id).orElse(null)
        );
    }

    @Override
    @Transactional
    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = messageRepository.save(
                mapper.toMessage(messageDto)
        );
        log.info("C: saved message with id {}", message.getId());

        try {
            String messagePayload = objectMapper.writeValueAsString(messageDto);

            OutboxMessage outboxMessage = OutboxMessage.builder()
                    .createdAt(LocalDateTime.now())
                    .payload(messagePayload)
                    .eventType("message")
                    .build();

            outboxRepository.save(outboxMessage);
        } catch (JsonProcessingException e) {
            log.error("C: error while converting json to string: {}", e.getMessage());
        }


        return messageDto;
    }
}
