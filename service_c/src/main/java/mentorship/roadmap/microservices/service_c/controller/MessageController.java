package mentorship.roadmap.microservices.service_c.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_c.dto.MessageDto;
import mentorship.roadmap.microservices.service_c.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/save")
    public ResponseEntity<MessageDto> saveMessage(@Valid @RequestBody MessageDto messageDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(messageDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.getMessage(id));
    }
}
