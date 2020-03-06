package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Department;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.services.DepartmentService;
import com.enesoral.simplehr.services.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/search")
    public String searchJobs(@RequestParam("search") String search, Model model) {
        model.addAttribute("jobs", jobService.searchJobs(search));
        return "jobs/index";
    }

    @PostMapping("/{id}/detail")
    public String getJobById(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        return "jobs/detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Job job = new Job();
        job.setDepartment(new Department());
        model.addAttribute("job", job);
        model.addAttribute("departments", departmentService.findAll());
        return "jobs/job-form";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        model.addAttribute("departments", departmentService.findAll());
        return "jobs/job-form";
    }

    @PostMapping("/saveOrUpdate")
    public String addJob(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/jobs/addform?error";
        }
        job.setPublishDate(LocalDateTime.now());
        jobService.save(job);
        return "redirect:/jobs/index?jobadded";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        jobService.deleteById(Long.parseLong(id));
        return "redirect:/jobs/index?jobdeleted";
    }
}
