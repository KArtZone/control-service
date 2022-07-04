package tech.inno.controlservice.domain;

import lombok.Data;
import tech.inno.controlservice.statemachine.state.ResourceState;

import java.util.UUID;

@Data
public class ResourceDto {

    private UUID id;

    private String title;

    private String description;

    private ResourceState state;

    private String createdByName;

}
