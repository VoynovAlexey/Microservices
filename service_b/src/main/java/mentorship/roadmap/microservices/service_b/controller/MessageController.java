package mentorship.roadmap.microservices.service_b.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_b.dto.MessageDto;
import mentorship.roadmap.microservices.service_b.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/process")
    public ResponseEntity<MessageDto> processMessage(@Valid @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageService.process(messageDto));
    }

    @GetMapping("/cache/{id}")
    public ResponseEntity<MessageDto> getCachedMessage(@PathVariable String id) {
        return ResponseEntity.ok().body(messageService.getCachedMessage(id));
    }

}
