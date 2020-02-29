package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.services.FileService;
import com.enesoral.simplehr.services.FileServiceImpl;
import com.enesoral.simplehr.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private FileService fileService;
    private UserService userService;

    public UserController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/resumeform")
    public String showUploadResumeForm() {
        return "/users/resume-form";
    }

    @PostMapping("/uploadresume")
    public String uploadResume(@RequestParam MultipartFile resume, HttpSession session) {
        if (!setAndUploadResume(userService.getLoggedUser(), resume)) {
            return "redirect:/users/resumeform?resumeerror";
        }
        return "redirect:/jobs/index?resumesuccess";
    }

    private boolean setAndUploadResume(User user, MultipartFile resume) {
        try {
            if (!userService.setAndUploadResume(user, fileService.byteArrayToFile(resume.getBytes()))) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
