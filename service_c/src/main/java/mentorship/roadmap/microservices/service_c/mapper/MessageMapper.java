package mentorship.roadmap.microservices.service_c.mapper;

import mentorship.roadmap.microservices.service_c.entity.Message;
import mentorship.roadmap.microservices.service_c.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * TODO Class Description
 *
 * @author Alexey Voynov
 * @since 28.01.2026
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = IGNORE
)
public interface MessageMapper {

    Message toMessage(MessageDto messageDto);

    MessageDto toMessageDto(Message message);

}
