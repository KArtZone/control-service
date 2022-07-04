package tech.inno.controlservice.statemachine.persist;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceStateMachinePersister implements StateMachinePersist<ResourceState, ResourceEvent, UUID> {

    //todo  Переделать хранение SM в бд
    private final Map<UUID, StateMachineContext<ResourceState, ResourceEvent>> contexts = new ConcurrentHashMap<>();

    @Override
    public void write(StateMachineContext<ResourceState, ResourceEvent> context, UUID key) {
        contexts.put(key, context);
    }

    @Override
    public StateMachineContext<ResourceState, ResourceEvent> read(UUID key) {
        return contexts.get(key);
    }
}
