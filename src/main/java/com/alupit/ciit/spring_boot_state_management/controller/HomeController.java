package com.alupit.ciit.spring_boot_state_management.controller;

import com.alupit.ciit.spring_boot_state_management.model.UserSession;
import com.alupit.ciit.spring_boot_state_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private UserSession userSession;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, HttpServletResponse response) {
        if(userSession.getUsername()==null) return "redirect:/login";
        // Set headers to prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
        model.addAttribute("userSession", userSession);
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        if (userService.validateUser(username,password)) {
            userSession.setUsername(username);
            return "redirect:/";
        } else {
            return "redirect:/login?error=invalid username or password";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userSession.setUsername(null);
        return "redirect:/login";
    }
}