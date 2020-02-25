package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.acl.Owner;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Job findByTitle(String title) {
        return jobRepository.findByTitle(title).orElse(null);
    }

    @Override
    public Job save(Job object) {
        return jobRepository.save(object);
    }

    @Override
    public Job findById(Long aLong) {
        return jobRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Job> findAll() {
        Set<Job> jobs = new HashSet<>();
        jobRepository.findAll().forEach(jobs::add);
        return jobs;
    }

    @Override
    public void delete(Job object) {
        jobRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        jobRepository.deleteById(aLong);
    }
}
