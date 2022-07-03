package tech.inno.controlservice.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class ResourceDto {

    private UUID id;

    private String title;

    private String description;

    private String createdByName;

}
