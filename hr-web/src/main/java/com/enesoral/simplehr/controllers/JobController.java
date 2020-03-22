package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Department;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.services.DepartmentService;
import com.enesoral.simplehr.services.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping({"/", "", "/index"})
    public String listJobs(Model model, @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "") String search) {
        model.addAttribute("jobs", jobService.searchJobs(search,
                PageRequest.of(page, 1, Sort.by("publishDate").descending())));
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
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
        model.addAttribute("departments", departmentService.findAll(PageRequest.of(0, Integer.MAX_VALUE)));
        return "jobs/job-form";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable String id, Model model) {
        model.addAttribute("job", jobService.findById(Long.parseLong(id)));
        model.addAttribute("departments", departmentService.findAll(PageRequest.of(0, Integer.MAX_VALUE)));
        return "jobs/job-form";
    }

    @PostMapping("/saveOrUpdate")
    public String addJob(@Valid Job job, BindingResult result, RedirectAttributes redirectAttr) {
        if (result.hasErrors()) {
            redirectAttr.addFlashAttribute("error", true);
            return "redirect:/jobs/add";
        }
        job.setPublishDate(LocalDateTime.now());
        jobService.save(job);
        redirectAttr.addFlashAttribute("jobsaved", true);
        return "redirect:/jobs/index";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable String id, RedirectAttributes redirectAttr) {
        jobService.deleteById(Long.parseLong(id));
        redirectAttr.addFlashAttribute("jobdeleted", true);
        return "redirect:/jobs/index";
    }
}
