package tech.inno.controlservice.statemachine.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.UUID;

import static tech.inno.controlservice.config.Constants.RESOURCE_ID;

@Slf4j
public class RegisterAction implements Action<ResourceState, ResourceEvent> {

    @Override
    public void execute(StateContext<ResourceState, ResourceEvent> stateContext) {
        final UUID resourceId = stateContext.getExtendedState().get(RESOURCE_ID, UUID.class);
        log.info("Resource with id: {} registered", resourceId);
    }
}
