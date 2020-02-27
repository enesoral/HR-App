package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping({"/", "", "/index"})
    public String listJobs(Model model){
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/index";
    }

    @RequestMapping("/detail/{id}")
    public String getJobById(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        return "jobs/detail";
    }
}
