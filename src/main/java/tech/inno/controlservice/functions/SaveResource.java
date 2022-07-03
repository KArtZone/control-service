package tech.inno.controlservice.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.inno.controlservice.domain.OpenDto;

import java.util.function.Function;

import static tech.inno.controlservice.config.Constants.REQUEST_ID;

@Slf4j
@Component
public class SaveResource implements Function<Message<OpenDto>, Message<OpenDto>> {

    @Override
    public Message<OpenDto> apply(final Message<OpenDto> message) {
        //todo  Добавить SSM
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        log.info("Saving resource: {}", message.getPayload());
        return message;
    }
}
