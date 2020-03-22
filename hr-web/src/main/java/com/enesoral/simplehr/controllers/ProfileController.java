package com.enesoral.simplehr.controllers;

import com.enesoral.simplehr.models.User;
import com.enesoral.simplehr.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public String initProfileForm(Model model) {
        User user = userService.findById(userService.getLoggedUser().getId());
        model.addAttribute("user", user);
        return "profiles/index";
    }

    @PostMapping("/update")
    public String processProfileForm(@ModelAttribute User user, Model model, RedirectAttributes redirectAttr) {
        User loggedUser = userService.findById(userService.getLoggedUser().getId());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setPhone(user.getPhone());
        loggedUser.setAddress(user.getAddress());
        User savedUser = userService.save(loggedUser);
        model.addAttribute("user", savedUser);
        redirectAttr.addFlashAttribute("upprofile", true);
        return "redirect:/profile/show";
    }
}
