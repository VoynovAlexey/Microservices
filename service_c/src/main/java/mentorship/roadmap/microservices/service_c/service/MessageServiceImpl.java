package mentorship.roadmap.microservices.service_c.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_c.entity.Message;
import mentorship.roadmap.microservices.service_c.dto.MessageDto;
import mentorship.roadmap.microservices.service_c.mapper.MessageMapper;
import mentorship.roadmap.microservices.service_c.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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

    private final MessageMapper mapper;

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Value("${kafka.topic.out}")
    private String outTopic;

    @Override
    public MessageDto getMessage(String id) {
        return mapper.toMessageDto(
                messageRepository.findById(id).orElse(null)
        );
    }

    @Override
    public MessageDto saveMessage(MessageDto messageDto) {
         Message message = messageRepository.save(
                mapper.toMessage(messageDto)
        );log.info("C: saved message with id {}", message.getId());

        kafkaTemplate.send(outTopic, messageDto).whenComplete(
                (result, ex) -> {
                    if (ex == null) {
                        log.info("Message successfully sent: {}", messageDto.message());
                    } else {
                        log.error("Failed to send message: {}", messageDto.message(), ex);
                    }
                }
        );

        return messageDto;
    }
}
