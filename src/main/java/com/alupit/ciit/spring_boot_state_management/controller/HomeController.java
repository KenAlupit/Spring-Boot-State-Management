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
    private UserSession userSession; // Autowired UserSession for managing user session data

    @Autowired
    private PasswordService passwordService; // Autowired PasswordService for user authentication

    @Autowired
    private CacheService cacheService; // Autowired CacheService for managing HTTP caching headers

    @Autowired
    private UserGrades userGrades; // Autowired UserGrades for managing user scores

    // Handler method for GET requests to root endpoint ("/")
    @GetMapping("/")
    public String home(HttpSession session, HttpServletResponse response) {
        // Redirect to login page if user is not logged in
        if(userSession.getUsername()==null){
            return "redirect:/login";
        }

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Determine exam message based on user's grade status
        if (userGrades.getUserGrades().get(userSession.getUsername()).equals(0)){
            session.setAttribute("examMessage", "Take a Quiz");
        }else{
            session.setAttribute("examMessage", "Retake a Quiz");
        }

        // Store user session and user grades in the session attributes for display in home view
        session.setAttribute("userSession", userSession);
        session.setAttribute("userGrades", userGrades.getUserGrades());

        // Return the name of the view (home.jsp in this case)
        return "home";
    }

    // Handler method for GET requests to "/login" endpoint
    @GetMapping("/login")
    public String showLoginForm() {
        // Return the name of the view (login.jsp in this case)
        return "login";
    }

    // Handler method for GET requests to "/grade" endpoint
    @GetMapping("/grade")
    public String showGrades(HttpServletResponse response) {
        // Redirect to login page if user is not logged in
        if(userSession.getUsername()==null){
            return "redirect:/login";
        }

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Return the name of the view (grade.jsp in this case)
        return "grade";
    }

    // Handler method for POST requests to "/login" endpoint
    @PostMapping("/login")
    public String login(String username, String password) {
        // Validate user credentials using PasswordService
        if (passwordService.validateUser(username,password)) {
            // Set the username in userSession upon successful login
            userSession.setUsername(username);
            return "redirect:/"; // Redirect to root endpoint ("/")
        } else {
            return "redirect:/login?error=invalid username or password"; // Redirect to login page with error message
        }
    }

    // Handler method for GET requests to "/logout" endpoint
    @GetMapping("/logout")
    public String logout() {
        // Clear username from userSession upon logout
        userSession.setUsername(null);
        return "redirect:/login"; // Redirect to login page after logout
    }
}