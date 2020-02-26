package com.enesoral.simplehr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @RequestMapping({"/", "", "/index"})
    public String jobIndex(){
        return "jobs/index";
    }
}
