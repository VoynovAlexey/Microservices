package mentorship.roadmap.microservices.service_b.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_b.dto.MessageDto;
import mentorship.roadmap.microservices.service_b.dto.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RedisTemplate<String, MessageDto> redisTemplate;
    private final WebClient.Builder webClienBuilder;

    private static final String KEY_PREFIX = "message:%s";
    private static final String SAVE_MESSAGE_URI = "/api/v1/save";

    @Value("${service-c.uri}")
    private String SERVICE_C_URI;

    @Override
    public MessageDto process(MessageDto messageDto) {
        if (messageDto.type() == Type.IMPORTANT) {
            redisTemplate.opsForValue().set(KEY_PREFIX.formatted(messageDto.id()), messageDto, Duration.ofMinutes(5));
            log.info("B: saved message with id: {}", messageDto.id());
        }
        sendMessagePost(messageDto);
        log.info("B: sent message with id: {}", messageDto.id());
        return messageDto;
    }

    @Override
    public MessageDto getCachedMessage(String id) {
        return redisTemplate.opsForValue().get(KEY_PREFIX.formatted(id));
    }

    private void sendMessagePost(MessageDto messageDto) {
        webClienBuilder.build()
                .post()
                .uri(SERVICE_C_URI + SAVE_MESSAGE_URI)
                .bodyValue(messageDto)
                .retrieve()
                .bodyToMono(MessageDto.class)
                .subscribe(
                        response -> log.info("B: got response with id: {}", response.id()),
                        error -> log.info("B: error sending request: {}", error.getMessage())
                );
    }
}
