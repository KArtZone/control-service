package tech.inno.controlservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import tech.inno.controlservice.statemachine.action.ErrorAction;
import tech.inno.controlservice.statemachine.action.RegisterAction;
import tech.inno.controlservice.statemachine.event.ResourceEvent;
import tech.inno.controlservice.statemachine.guard.CheckPoliciesGuard;
import tech.inno.controlservice.statemachine.listener.ResourceStateMachineListener;
import tech.inno.controlservice.statemachine.persist.ResourceStateMachinePersister;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.EnumSet;
import java.util.UUID;

import static tech.inno.controlservice.statemachine.event.ResourceEvent.REGISTER;
import static tech.inno.controlservice.statemachine.state.ResourceState.DRAFT;
import static tech.inno.controlservice.statemachine.state.ResourceState.REGISTERED;

@Configuration
@EnableStateMachineFactory
public class ResourceStateMachineConfig extends EnumStateMachineConfigurerAdapter<ResourceState, ResourceEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ResourceState, ResourceEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new ResourceStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<ResourceState, ResourceEvent> states) throws Exception {
        states.withStates()
                .initial(DRAFT)
                .end(REGISTERED)
                .states(EnumSet.allOf(ResourceState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ResourceState, ResourceEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(DRAFT)
                .target(REGISTERED)
                .event(REGISTER)
                .guard(checkPoliciesGuard())
                .action(registerAction(), errorAction());
    }

    @Bean
    public Action<ResourceState, ResourceEvent> registerAction() {
        return new RegisterAction();
    }

    @Bean
    public Action<ResourceState, ResourceEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Guard<ResourceState, ResourceEvent> checkPoliciesGuard() {
        return new CheckPoliciesGuard();
    }

    @Bean
    public StateMachinePersister<ResourceState, ResourceEvent, UUID> persister() {
        return new DefaultStateMachinePersister<>(new ResourceStateMachinePersister());
    }
}
