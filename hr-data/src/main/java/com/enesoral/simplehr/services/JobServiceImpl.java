package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Job findByTitle(String title) {
        return jobRepository.findByTitle(title).orElse(null);
    }

    @Override
    public Page<Job> searchJobs(String search, Pageable pageable) {
        return jobRepository.findAllByTitleContaining(search, pageable);
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
    public Page<Job> findAll(Pageable pageable) {
        return jobRepository.findAll(pageable);
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
