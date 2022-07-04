package tech.inno.controlservice.statemachine.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

@Slf4j
public class ResourceStateMachineListener implements StateMachineListener<ResourceState, ResourceEvent> {

    @Override
    public void stateChanged(State<ResourceState, ResourceEvent> state, State<ResourceState, ResourceEvent> state1) {
        log.info("Resource changed state from {} to {}", state, state1);
    }

    @Override
    public void stateEntered(State<ResourceState, ResourceEvent> state) {

    }

    @Override
    public void stateExited(State<ResourceState, ResourceEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<ResourceEvent> message) {

    }

    @Override
    public void transition(Transition<ResourceState, ResourceEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<ResourceState, ResourceEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<ResourceState, ResourceEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<ResourceState, ResourceEvent> stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine<ResourceState, ResourceEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<ResourceState, ResourceEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<ResourceState, ResourceEvent> stateContext) {

    }
}
