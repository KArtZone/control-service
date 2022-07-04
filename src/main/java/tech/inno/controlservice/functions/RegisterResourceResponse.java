package tech.inno.controlservice.functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.inno.controlservice.domain.ResourceDto;
import tech.inno.controlservice.service.ResourceService;

import java.util.function.Function;

import static tech.inno.controlservice.config.Constants.REQUEST_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterResourceResponse implements Function<Message<ResourceDto>, Message<ResourceDto>> {

    private final ResourceService service;

    @Override
    public Message<ResourceDto> apply(final Message<ResourceDto> message) {
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        service.register(message.getPayload());
        log.info("Registered resource: {}", message.getPayload());
        return message;
    }
}
