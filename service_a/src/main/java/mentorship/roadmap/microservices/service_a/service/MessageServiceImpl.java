package mentorship.roadmap.microservices.service_a.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_a.config.WebConfig;
import mentorship.roadmap.microservices.service_a.dto.MessageDto;
import mentorship.roadmap.microservices.service_a.dto.MessageRequest;
import mentorship.roadmap.microservices.service_a.entity.Message;
import mentorship.roadmap.microservices.service_a.mapper.MessageMapper;
import mentorship.roadmap.microservices.service_a.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageServiceImpl implements MessageService{

    private final WebClient.Builder webClientBuilder;
    private final MessageRepository messageRepository;
    private final MessageMapper mapper;

    private static final String PROCESS_MESSAGE_URI = "/api/v1/process";

    @Value("${service-b.uri}")
    private String SERVICE_B_URI;

    @Override
    public MessageRequest processMessage(MessageDto messageDto) {
        Message message = save(messageDto);
        log.info("A: saved message with id {}", message.getId());
        MessageRequest messageRequest = mapper.toMessageRequest(message);
        sendMessagePost(messageRequest);
        log.info("A: sent message with id: {}", messageRequest.id());
        return messageRequest;
    }

    @Override
    public MessageDto findById(String id) {
        return mapper.toMessageDto(
                messageRepository.findById(id)
                        .orElseThrow()
        );
    }

    private void sendMessagePost(MessageRequest messageRequest) {
         webClientBuilder.build()
                .post()
                .uri(SERVICE_B_URI + PROCESS_MESSAGE_URI)
                .bodyValue(messageRequest)
                .retrieve()
                .bodyToMono(MessageRequest.class)
                 .subscribe(
                         response -> log.info("A: got response with id: {}", response.id()),
                         error -> log.info("A: error sending request: {}", error.getMessage())
                 );
    }

    private Message save(MessageDto messageDto) {
        return messageRepository.save(
                mapper.toMessage(messageDto)
        );
    }
}
