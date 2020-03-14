package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.services.FileService;
import com.enesoral.simplehr.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

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
    public String uploadResume(@RequestParam MultipartFile resume, RedirectAttributes redirectAttr) {
        if (!setAndUploadResume(userService.getLoggedUser(), resume)) {
            return "redirect:/users/resumeform";
        }
        redirectAttr.addFlashAttribute("resumesuccess", true);
        return "redirect:/jobs/index";
    }

    @PostMapping("/{id}/showresume")
    public void showResume(@PathVariable String id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String userName = userService.findById(Long.parseLong(id)).getUsername();
        InputStream inputStream =
                new FileInputStream(new File(System.getProperty("user.dir") + "/uploads/" + userName + "-resume"));
        int nRead;
        while ((nRead = inputStream.read()) != -1) {
            response.getWriter().write(nRead);
        }
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
