package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.Application;
import com.enesoral.simplehr.models.Job;
import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.services.ApplicationService;
import com.enesoral.simplehr.services.JobService;
import com.enesoral.simplehr.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JobService jobService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService, JobService jobService, UserService userService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping({"/index"})
    public String listApplications(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("applications",
                applicationService.findAll(PageRequest.of(page, 1, Sort.by("applicationDate").descending())));
        model.addAttribute("currentPage", page);
        return "applications/index";
    }

    @GetMapping({"/mine"})
    public String listUserApplications(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("applications",
                applicationService.findUserApplications(userService.getLoggedUser(), PageRequest.of(page, 1, Sort.by("applicationDate").descending())));
        model.addAttribute("currentPage", page);
        return "applications/user-applications";
    }

    @PostMapping("/{id}/detail")
    public String getApplicationById(@PathVariable String id, Model model){
        model.addAttribute("app", applicationService.findById(Long.parseLong(id)));
        return "applications/detail";
    }

    @GetMapping("/{id}/applyform")
    public String showApplyForm(@PathVariable String id, Model model, RedirectAttributes redirectAttr) {
        Job job = jobService.findById(Long.parseLong(id));
        if (isAlreadyApplied(job, userService.getLoggedUser())) {
            redirectAttr.addFlashAttribute("alreadyapplied", true);
            return "redirect:/jobs/index";
        }

        if (userService.getLoggedUser().getResumeDirectory() == null) {
            redirectAttr.addFlashAttribute("haveto", true);
            return "redirect:/users/resumeform";
        }

        model.addAttribute("jobId", Long.parseLong(id));
        return "applications/apply-form";
    }

    @GetMapping("/{id}/update")
    public String updateApplication(@PathVariable String id, Model model) throws IllegalAccessException {
        Application application = applicationService.findById(Long.parseLong(id));
        if (isApplicationBelongUser(application, userService.getLoggedUser())) {
            model.addAttribute("app", application);
            return "applications/apply-form";
        }
        throw new IllegalAccessException("Have no authority for this operation.");
    }

    @PostMapping("/{id}/apply")
    public String applyJob(@PathVariable String id, @RequestParam("thoughts") String thoughts,
                           @RequestParam("appId") String appId, RedirectAttributes redirectAttr) {
        Application application;
        if (appId.length() != 0) {
            application = applicationService.findById(Long.parseLong(appId));
            application.setThoughtsOnJob(thoughts);
        } else {
            application = new Application(LocalDateTime.now(), userService.getLoggedUser(),
                    jobService.findById(Long.parseLong(id)), thoughts);
        }

        applicationService.save(application);
        redirectAttr.addFlashAttribute("applysuccess", true);
        return "redirect:/jobs/index";
    }

    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable String id, RedirectAttributes redirectAttr) {
        applicationService.deleteById(Long.parseLong(id));
        redirectAttr.addFlashAttribute("appdeleted", true);
        return "redirect:/applications/index";
    }

    @PostMapping("/{id}/withdraw")
    public String withdrawTheApplication(@PathVariable String id, RedirectAttributes redirectAttr)
            throws IllegalAccessException {
        Application application = applicationService.findById(Long.parseLong(id));
        if (isApplicationBelongUser(application, userService.getLoggedUser())) {
            applicationService.deleteById(Long.parseLong(id));
            redirectAttr.addFlashAttribute("appdeleted", true);
            return "redirect:/applications/mine";
        }
        throw new IllegalAccessException("Have no authority for this operation.");
    }

    private boolean isAlreadyApplied(Job job, User user) {
        return applicationService.isAlreadyApplied(job, user);
    }

    private boolean isApplicationBelongUser(Application application, User user) {
        return application.getUser().getId().equals(user.getId());
    }
}
