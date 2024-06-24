package com.alupit.ciit.spring_boot_state_management.controller;

import com.alupit.ciit.spring_boot_state_management.model.UserGrades;
import com.alupit.ciit.spring_boot_state_management.model.UserSession;
import com.alupit.ciit.spring_boot_state_management.service.PasswordService;
import com.alupit.ciit.spring_boot_state_management.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserSession userSession;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserGrades userGrades;

    @GetMapping("/")
    public String home(HttpSession session, HttpServletResponse response) {
        if(userSession.getUsername()==null) return "redirect:/login";
        cacheService.setNoCacheHeaders(response);
        if (userGrades.getUserGrades().get(userSession.getUsername()).equals(0)){
            session.setAttribute("examMessage", "Take a Quiz");
        }else{
            session.setAttribute("examMessage", "Retake a Quiz");
        }
        session.setAttribute("userSession", userSession);
        session.setAttribute("userGrades", userGrades.getUserGrades());
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/grade")
    public String showGrades(HttpServletResponse response) {
        if(userSession.getUsername()==null) return "redirect:/login";
        cacheService.setNoCacheHeaders(response);
        return "grade";
    }


    @PostMapping("/login")
    public String login(String username, String password) {
        if (passwordService.validateUser(username,password)) {
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