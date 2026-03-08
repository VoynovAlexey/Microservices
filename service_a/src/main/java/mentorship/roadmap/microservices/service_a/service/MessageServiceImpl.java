package mentorship.roadmap.microservices.service_a.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_a.client.MessageClient;
import mentorship.roadmap.microservices.service_a.dto.MessageDto;
import mentorship.roadmap.microservices.service_a.dto.MessageRequest;
import mentorship.roadmap.microservices.service_a.entity.Message;
import mentorship.roadmap.microservices.service_a.mapper.MessageMapper;
import mentorship.roadmap.microservices.service_a.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper mapper;
    private final MessageClient messageClient;

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
        CompletableFuture.supplyAsync(() -> messageClient.sendMessage(messageRequest))
                .whenComplete((response, error) -> {
                    if (error != null) {
                        log.error("A: error sending request: {}", error.getMessage());
                    } else {
                        log.info("A: got response with id: {}", response.id());
                    }
                });
    }

    private Message save(MessageDto messageDto) {
        return messageRepository.save(
                mapper.toMessage(messageDto)
        );
    }
}
