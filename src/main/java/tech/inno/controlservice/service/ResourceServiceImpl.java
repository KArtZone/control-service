package tech.inno.controlservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.inno.controlservice.domain.ResourceDto;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Override
    public boolean applyPolicies(ResourceDto resourceDto) {
        //todo  Добавить расчет политик
        return resourceDto != null;
    }
}
