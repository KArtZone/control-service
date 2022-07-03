package tech.inno.controlservice.service;

import tech.inno.controlservice.domain.ResourceDto;

public interface ResourceService {

    boolean applyPolicies(ResourceDto resourceDto);


}
