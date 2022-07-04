package tech.inno.controlservice.service;

import tech.inno.controlservice.domain.ResourceDto;

import java.util.List;

public interface ResourceService {

    boolean create(ResourceDto resourceDto);

    boolean register(ResourceDto resourceDto);

    List<String> getTransitions(ResourceDto resourceDto);

}
