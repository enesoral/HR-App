package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {
    private static final String TITLE = "Software Engineer";

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobServiceImpl service;

    Job job;

    @BeforeEach
    void setUp() {
        job = Job.builder().title(TITLE).publishDate(LocalDateTime.now()).build();
    }

    @Test
    void findByTitle() {
        when(jobRepository.findByTitle(TITLE)).thenReturn(Optional.of(job));
        Job job = service.findByTitle(TITLE);
        verify(jobRepository, times(1)).findByTitle(TITLE);
        assertEquals(TITLE, job.getTitle());
    }

    @Test
    void save() {
        when(jobRepository.save(any())).thenReturn(job);
        Job savedJob = service.save(job);
        assertNotNull(savedJob);
    }

    @Test
    void findAll() {
        Set<Job> jobs = new TreeSet<>();
        jobs.add(job);
        when(jobRepository.findAll()).thenReturn(jobs);
        Set<Job> returnedSet = service.findAll();
        assertEquals(jobs.size(), returnedSet.size());
    }

    @Test
    void delete() {
        service.delete(job);
        verify(jobRepository).delete(job);
    }
}