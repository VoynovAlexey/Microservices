package mentorship.roadmap.microservices.service_a.exception;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 08.03.2026
 */
public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(String id) {
        super("Message with id %s not found".formatted(id));
    }
}
