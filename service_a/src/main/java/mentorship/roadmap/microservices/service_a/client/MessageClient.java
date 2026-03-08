package mentorship.roadmap.microservices.service_a.client;

import mentorship.roadmap.microservices.service_a.dto.MessageRequest;
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

    @PostMapping("/api/v1/process")
    MessageRequest sendMessage(MessageRequest messageRequest);

}
