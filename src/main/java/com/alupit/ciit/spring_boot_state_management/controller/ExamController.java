package com.alupit.ciit.spring_boot_state_management.controller;

import java.util.*;

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
    private ExamService examService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserGrades userGrades;

    @Autowired
    private UserSession userSession;

    // Create a list of questions and their corresponding options
    public List<String[]> questionsAndOptions = new ArrayList<>();

    public HashMap<String, String> userResponses = new HashMap<String, String>();

    public 	HashMap<String, String> answerKey = new HashMap<String, String>();

    public int scorePercentage = 0;

    public int score = 0;

    public ExamController() {
        questionsAndOptions.add(new String[]{"What country has the highest life expectancy?", "Philippines", "Hong Kong", "USA", "Singapore"});
        questionsAndOptions.add(new String[]{"How many elements are in the periodic table?", "119", "118", "117", "120"});
        questionsAndOptions.add(new String[]{"Who was the Ancient Greek God of the Sun?", "Athena", "Hades", "Apollo", "Venus"});
        questionsAndOptions.add(new String[]{"What country drinks the most coffee per capita?", "Russia", "China", "USA", "Finland"});
        questionsAndOptions.add(new String[]{"What Renaissance artist is buried in Rome's Pantheon?", "Donatello", "Leonardo", "Michael", "Raphael"});
    }

    @GetMapping("/exam")
    public String showExam(Model model, HttpServletResponse response) {
        if(userSession.getUsername()==null) return "redirect:/login";
        // Shuffle options for each question
        for (String[] questionAndOptions : questionsAndOptions) {
            shuffleArrayElements(questionAndOptions, 1, questionAndOptions.length - 1);
        }

        // Shuffle the entire list of questions (optional)
        Collections.shuffle(questionsAndOptions);

        model.addAttribute("questionsAndOptions", questionsAndOptions);

        cacheService.setNoCacheHeaders(response);

        return "exam";
    }

    // Helper method to shuffle array elements from start index to end index
    private void shuffleArrayElements(String[] array, int startIndex, int endIndex) {
        List<String> subList = Arrays.asList(array).subList(startIndex, endIndex + 1);
        Collections.shuffle(subList);
        for (int i = startIndex; i <= endIndex; i++) {
            array[i] = subList.get(i - startIndex);
        }
    }

    @PostMapping("/exam")
    public String showScore(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if(userSession.getUsername()==null) return "redirect:/login";
        //Retrieve user answers from request parameters and add them to the questionAnswers map
        userResponses.put("highLifeExpectancy",request.getParameter("what_country_has_the_highest_life_expectancy"));
        userResponses.put("periodicTableElements",request.getParameter("how_many_elements_are_in_the_periodic_table"));
        userResponses.put("sunGreekGod",request.getParameter("who_was_the_ancient_greek_god_of_the_sun"));
        userResponses.put("coffeePerCapita",request.getParameter("what_country_drinks_the_most_coffee_per_capita"));
        userResponses.put("romeRenaissanceArtist",request.getParameter("what_renaissance_artist_is_buried_in_rome's_pantheon"));

        score = examService.checkExam(userResponses);

        //Calculate percentage score
        scorePercentage = (int)((double)score / examService.answerKey.size() * 100);

        userGrades.setUserGrade(userSession.getUsername(), scorePercentage);

        // Print the user grades
        System.out.println("User Grades:");
        for (Map.Entry<String, Integer> entry : userGrades.getUserGrades().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        session.setAttribute("answerKeySize", examService.answerKey.size());
        session.setAttribute("scorePercentage", scorePercentage);
        session.setAttribute("score", score);

        cacheService.setNoCacheHeaders(response);

        return "score";
    }
}