package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public boolean isAlreadyApplied(Job job, User user) {
        return applicationRepository.findApplicationByJobAndUser(job, user).isPresent();
    }

    @Override
    public Page<Application> findUserApplications(User user, Pageable pageable) {
        return applicationRepository.findAllByUser(user, pageable);
    }

    @Override
    public Application save(Application object) {
        object.getJob().setLastApplicationDate(LocalDate.now());
        return applicationRepository.save(object);
    }

    @Override
    public Application findById(Long aLong) {
        if(!applicationRepository.findById(aLong).isPresent()) {
            throw new RuntimeException("Application not found for id value: " + aLong);
        }
        return applicationRepository.findById(aLong).get();
    }

    @Override
    public Page<Application> findAll(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    @Override
    public void delete(Application object) {
        applicationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        if(!applicationRepository.findById(aLong).isPresent()) {
            throw new RuntimeException("Application not found for id value: " + aLong);
        }
        applicationRepository.deleteById(aLong);
    }
}
