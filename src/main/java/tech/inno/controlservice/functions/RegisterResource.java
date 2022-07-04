package tech.inno.controlservice.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tech.inno.controlservice.domain.ResourceDto;

import java.util.function.Function;

import static tech.inno.controlservice.config.Constants.REQUEST_ID;
import static tech.inno.controlservice.statemachine.state.ResourceState.REGISTERED;

@Slf4j
@Component
public class RegisterResource implements Function<Message<ResourceDto>, Message<ResourceDto>> {

    @Override
    public Message<ResourceDto> apply(Message<ResourceDto> message) {
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        final ResourceDto resourceDto = message.getPayload();
        log.info("Processing (register) resource: {}", resourceDto);
        resourceDto.setState(REGISTERED);
        return MessageBuilder.withPayload(resourceDto)
                .setHeader(REQUEST_ID, requestId)
                .build();
    }
}
