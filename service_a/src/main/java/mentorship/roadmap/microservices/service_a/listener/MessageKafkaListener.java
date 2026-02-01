package mentorship.roadmap.microservices.service_a.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_a.dto.MessageDto;
import mentorship.roadmap.microservices.service_a.service.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MessageKafkaListener {

    private final MessageService messageService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "in")
    public void listenMessageEvent(String jsonMessage) {
         try {
             MessageDto messageDto = objectMapper.readValue(jsonMessage, MessageDto.class);
             log.info("A: got message from kafka: {}", messageDto.message());
             messageService.processMessage(messageDto);
         } catch (JsonProcessingException e) {
             log.error("A: Failed to parse message: {}", e.getMessage());
         }
    }

}
