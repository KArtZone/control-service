package tech.inno.controlservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import tech.inno.controlservice.domain.ResourceDto;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static tech.inno.controlservice.config.Constants.*;
import static tech.inno.controlservice.statemachine.event.ResourceEvent.REGISTER;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final StateMachineFactory<ResourceState, ResourceEvent> factory;

    private final StateMachinePersister<ResourceState, ResourceEvent, UUID> persister;

    @Override
    public boolean create(ResourceDto resourceDto) {
        final UUID resourceId = resourceDto.getId();
        final StateMachine<ResourceState, ResourceEvent> stateMachine = factory.getStateMachine();
        stateMachine.getExtendedState()
                .getVariables()
                .put(RESOURCE_ID, resourceId);
        try {
            persister.persist(stateMachine, resourceId);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean register(ResourceDto resourceDto) {
        final UUID resourceId = resourceDto.getId();
        final StateMachine<ResourceState, ResourceEvent> stateMachine = factory.getStateMachine();
        try {
            persister.restore(stateMachine, resourceId);
            final Map<Object, Object> variables = stateMachine
                    .getExtendedState()
                    .getVariables();
            variables.put(RESOURCE, resourceDto);
            //todo  Тут можно передать рассчет политик для guard
            variables.put(POLICIES, 1 == 1);
            stateMachine.sendEvent(REGISTER);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<String> getTransitions(ResourceDto resourceDto) {
        final UUID resourceId = resourceDto.getId();
        final StateMachine<ResourceState, ResourceEvent> stateMachine = factory.getStateMachine();
        try {
            persister.persist(stateMachine, resourceId);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
        return stateMachine.getTransitions()
                .stream()
                .map(Transition::getName)
                .collect(Collectors.toList());
    }
}
