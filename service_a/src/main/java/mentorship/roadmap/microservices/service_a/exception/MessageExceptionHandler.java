package mentorship.roadmap.microservices.service_a.exception;

import mentorship.roadmap.microservices.service_a.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 08.03.2026
 */
@RestControllerAdvice
public class MessageExceptionHandler {

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ErrorDto> handleMessageNotFoundError(MessageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("сообщение не найдено"));
    }

}
