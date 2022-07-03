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
public class ConsumeResource implements Function<Message<ResourceDto>, Message<ResourceDto>> {

    private final ResourceService resourceService;

    @Override
    public Message<ResourceDto> apply(final Message<ResourceDto> message) {
        //todo добавить вычисление политик
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        log.info("Consume resource: {}", message.getPayload());
        return message;
    }
}
