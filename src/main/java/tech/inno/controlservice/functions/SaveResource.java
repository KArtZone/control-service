package tech.inno.controlservice.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tech.inno.controlservice.domain.ResourceDto;

import java.util.function.Function;

import static tech.inno.controlservice.config.Constants.REQUEST_ID;
import static tech.inno.controlservice.statemachine.state.ResourceState.DRAFT;

@Slf4j
@Component
public class SaveResource implements Function<Message<ResourceDto>, Message<ResourceDto>> {

    @Override
    public Message<ResourceDto> apply(final Message<ResourceDto> message) {
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        final ResourceDto resourceDto = message.getPayload();
        log.info("Saving resource: {}", resourceDto);
        resourceDto.setState(DRAFT);
        return MessageBuilder.withPayload(resourceDto)
                .setHeader(REQUEST_ID, requestId)
                .build();
    }
}
