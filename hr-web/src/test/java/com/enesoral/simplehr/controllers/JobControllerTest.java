package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.services.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    @Mock
    JobService jobService;

    @InjectMocks
    JobController controller;

    Set<Job> jobs;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        jobs = new HashSet<>();
        jobs.add(Job.builder().title("Software Engineer").build());
        jobs.add(Job.builder().title("Intern").build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listJobs() throws Exception {
        when(jobService.findAll()).thenReturn(jobs);
        mockMvc.perform(get("/jobs/"))
                .andExpect(status().isOk())
                .andExpect(view().name("jobs/index"))
                .andExpect(model().attribute("jobs", hasSize(2)));
    }

    @Test
    void listJobsByIndex() throws Exception {
        when(jobService.findAll()).thenReturn(jobs);
        mockMvc.perform(get("/jobs/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("jobs/index"))
                .andExpect(model().attribute("jobs", hasSize(2)));
    }
}