package mentorship.roadmap.microservices.service_b.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mentorship.roadmap.microservices.service_b.client.MessageClient;
import mentorship.roadmap.microservices.service_b.dto.MessageDto;
import mentorship.roadmap.microservices.service_b.dto.Type;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

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
    private final MessageClient messageClient;

    private static final String KEY_PREFIX = "message:%s";


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
        CompletableFuture.supplyAsync(() -> messageClient.sendMessage(messageDto))
                .whenComplete((response, error) -> {
                    if (error != null) {
                        log.error("B: error sending request: {}", error.getMessage());
                    } else {
                        log.info("B: error sending request: {}", error.getMessage());
                    }
                });
    }
}
