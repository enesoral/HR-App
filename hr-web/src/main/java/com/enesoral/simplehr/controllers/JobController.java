package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping({"/", "", "/index"})
    public String listJobs(Model model){
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/index";
    }

    @PostMapping("/{id}/detail")
    public String getJobById(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        return "jobs/detail";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        jobService.deleteById(Long.parseLong(id));
        return "redirect:/jobs/index";
    }
}
