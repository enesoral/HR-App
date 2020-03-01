package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public boolean isAlreadyApplied(Job job, User user) {
        for (Application application: applicationRepository.findAll()) {
            if (application.getJob().equals(job) && application.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Application save(Application object) {
        object.getJob().setLastApplicationDate(LocalDate.now());
        return applicationRepository.save(object);
    }

    @Override
    public Application findById(Long aLong) {
        return applicationRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Application> findAll() {
        Set<Application> applications = new TreeSet<>();
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
