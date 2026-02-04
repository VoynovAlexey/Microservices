package mentorship.roadmap.microservices.service_b.client;

import mentorship.roadmap.microservices.service_b.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 04.02.2026
 */
@FeignClient(name = "messageClient")
public interface MessageClient {

    @PostMapping("/api/v1/save")
    MessageDto sendMessage(MessageDto messageDto);

}
