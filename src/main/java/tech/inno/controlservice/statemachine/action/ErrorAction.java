package tech.inno.controlservice.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.UUID;

import static tech.inno.controlservice.config.Constants.RESOURCE_ID;

@Slf4j
public class ErrorAction implements Action<ResourceState, ResourceEvent> {

    @Override
    public void execute(StateContext<ResourceState, ResourceEvent> stateContext) {
        final UUID resourceId = stateContext.getExtendedState().get(RESOURCE_ID, UUID.class);
        final Message<ResourceEvent> message = stateContext.getMessage();
        log.info("Cannot process resource with id: {}. Error message: {} ", resourceId, message);
    }
}
