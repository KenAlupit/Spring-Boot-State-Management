package com.alupit.ciit.spring_boot_state_management.controller;

import com.alupit.ciit.spring_boot_state_management.model.UserSession;
import com.alupit.ciit.spring_boot_state_management.service.CacheService;
import com.alupit.ciit.spring_boot_state_management.service.ExamService;
import com.alupit.ciit.spring_boot_state_management.model.UserGrades;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;  // Autowired ExamService for handling exam-related business logic

    @Autowired
    private CacheService cacheService;  // Autowired CacheService for managing HTTP caching headers

    @Autowired
    private UserGrades userGrades;  // Autowired UserGrades for managing user scores

    @Autowired
    private UserSession userSession;  // Autowired UserSession for managing user session data

    public int scorePercentage = 0;  // Instance variable to store percentage score
    public int score = 0;  // Instance variable to store raw score

    // Handler method for GET requests to /exam endpoint
    @GetMapping("/exam")
    public String showExam(Model model, HttpServletResponse response) {
        // Redirect to login page if user session does not have a username (not logged in)
        if(userSession.getUsername()==null) {
            return "redirect:/login";
        }

        // Add questions and options to the model for rendering in the exam view
        model.addAttribute("questionsAndOptions", examService.getQuestionsAndOptions());

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Return the name of the view (exam.jsp in this case)
        return "exam";
    }

    // Handler method for POST requests to /exam endpoint
    @PostMapping("/exam")
    public String showScore(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // Redirect to login page if user session does not have a username (not logged in)
        if(userSession.getUsername()==null) {
            return "redirect:/login";
        }

        // Calculate score by checking user's answers against the answer key
        score = examService.checkExam(request);

        //Calculate percentage score
        scorePercentage = (int)((double)score / examService.answerKey.size() * 100);

        // Update user's grade based on username and calculated percentage score
        userGrades.setUserGrade(userSession.getUsername(), scorePercentage);

        // Store score related attributes in the session for display in score view
        session.setAttribute("answerKeySize", examService.answerKey.size());
        session.setAttribute("scorePercentage", scorePercentage);
        session.setAttribute("score", score);

        // Set HTTP headers to prevent caching
        cacheService.setNoCacheHeaders(response);

        // Return the name of the view (score.jsp in this case)
        return "score";
    }
}