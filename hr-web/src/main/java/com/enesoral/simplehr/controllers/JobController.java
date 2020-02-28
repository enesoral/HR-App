package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.services.DepartmentService;
import com.enesoral.simplehr.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final DepartmentService departmentService;

    public JobController(JobService jobService, DepartmentService departmentService) {
        this.jobService = jobService;
        this.departmentService = departmentService;
    }

    @GetMapping({"/", "", "/index"})
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/index";
    }

    @PostMapping("/{id}/detail")
    public String getJobById(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        return "jobs/detail";
    }

    @GetMapping("/addform")
    public String showJobAddForm(Model model, Job job) {
        model.addAttribute("departments", departmentService.findAll());
        return "jobs/add-job";
    }

    @PostMapping("/addjob")
    public String addJob(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/jobs/addform?error";
        }
        job.setPublishDate(LocalDateTime.now());
        jobService.save(job);
        return "redirect:/jobs/index";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        jobService.deleteById(Long.parseLong(id));
        return "redirect:/jobs/index";
    }
}
