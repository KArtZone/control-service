package tech.inno.controlservice.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.inno.controlservice.domain.OpenDto;

import java.util.UUID;
import java.util.function.Function;

import static tech.inno.controlservice.config.Constants.REQUEST_ID;

@Slf4j
@Component
public class OpenResource implements Function<Message<OpenDto>, Message<OpenDto>> {

    @Override
    public Message<OpenDto> apply(final Message<OpenDto> message) {
        final OpenDto openDto = message.getPayload();
        final UUID id = openDto.getId();
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        log.info("Processing (open) resource with id: {}", id);
        return message;
    }
}
