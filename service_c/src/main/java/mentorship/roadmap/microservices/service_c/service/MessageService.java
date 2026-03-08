package mentorship.roadmap.microservices.service_c.service;

import mentorship.roadmap.microservices.service_c.dto.MessageDto;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
public interface MessageService {
    MessageDto saveMessage(MessageDto messageDto);

    MessageDto getMessage(String id);
}
