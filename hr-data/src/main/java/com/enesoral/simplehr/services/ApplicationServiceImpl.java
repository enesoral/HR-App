package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public Application save(Application object) {
        return applicationRepository.save(object);
    }

    @Override
    public Application findById(Long aLong) {
        return applicationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Application> findAll() {
        Set<Application> applications = new HashSet<>();
        applicationRepository.findAll().forEach(applications::add);
        return applications;
    }

    @Override
    public void delete(Application object) {
        applicationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        applicationRepository.deleteById(aLong);
    }
}
