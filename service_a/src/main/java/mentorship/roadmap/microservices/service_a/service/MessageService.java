package mentorship.roadmap.microservices.service_a.service;

import mentorship.roadmap.microservices.service_a.dto.MessageDto;
import mentorship.roadmap.microservices.service_a.dto.MessageRequest;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
public interface MessageService {

    MessageRequest processMessage(MessageDto messageDto);

    MessageDto findById(String id);
}
