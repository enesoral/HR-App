package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.services.JobService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableSpringDataWebSupport
@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    @Mock
    JobService jobService;

    @InjectMocks
    JobController controller;

    List<Job> jobs;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        jobs = new ArrayList<>();
        jobs.add(Job.builder().title("Software Engineer").build());
        jobs.add(Job.builder().title("Intern").build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void listJobsByIndex() throws Exception {
        when(jobService.searchJobs(anyString(), ArgumentMatchers.isA(Pageable.class))).thenReturn(new PageImpl<>(jobs));
        mockMvc.perform(get("/jobs/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("jobs/index"))
                .andExpect(model().attributeExists("jobs"));

    }
}