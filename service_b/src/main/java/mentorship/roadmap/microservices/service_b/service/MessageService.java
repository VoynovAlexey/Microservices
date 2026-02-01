package mentorship.roadmap.microservices.service_b.service;

import mentorship.roadmap.microservices.service_b.dto.MessageDto;

import java.util.List;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
public interface MessageService {
    MessageDto process(MessageDto messageDto);

    MessageDto getCachedMessage(String id);
}
