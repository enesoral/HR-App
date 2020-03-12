package com.enesoral.simplehr.services;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

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
        List<Job> jobs = new ArrayList<>();
        jobs.add(job);
        when(jobRepository.findAll(isA(Pageable.class))).thenReturn(new PageImpl<>(jobs));
        Page<Job> returnedSet = service.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        assertEquals(jobs.size(), returnedSet.getTotalElements());
    }

    @Test
    void delete() {
        service.delete(job);
        verify(jobRepository).delete(job);
    }
}