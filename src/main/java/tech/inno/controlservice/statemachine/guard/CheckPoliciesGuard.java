package tech.inno.controlservice.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

import static tech.inno.controlservice.config.Constants.POLICIES;

public class CheckPoliciesGuard implements Guard<ResourceState, ResourceEvent> {

    @Override
    public boolean evaluate(StateContext<ResourceState, ResourceEvent> stateContext) {
        return stateContext.getExtendedState()
                .get(POLICIES, Boolean.class);
    }
}
