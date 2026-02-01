package mentorship.roadmap.microservices.service_a.controller;

import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_a.dto.MessageDto;
import mentorship.roadmap.microservices.service_a.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 30.01.2026
 */
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable String id) {
        return messageService.findById(id);
    }

}
