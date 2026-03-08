package mentorship.roadmap.microservices.service_b.exception;

import mentorship.roadmap.microservices.service_b.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 08.03.2026
 */
@RestControllerAdvice
public class MessageExceptionHandler {

    private static final String WRONG_FIELDS_ERROR = "Некорректно заполнены поля";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, doubling) -> existing + ", " + doubling
                ));
        return ResponseEntity.badRequest().body(new ErrorDto(WRONG_FIELDS_ERROR ,errors));
    }

}
