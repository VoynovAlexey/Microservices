package mentorship.roadmap.microservices.service_a.entity;

import lombok.Data;
import mentorship.roadmap.microservices.service_a.dto.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 29.01.2026
 */
@Data
@Document(collection = "message")
public class Message {

    @Id
    String id;

    String message;

    Type type;

}
