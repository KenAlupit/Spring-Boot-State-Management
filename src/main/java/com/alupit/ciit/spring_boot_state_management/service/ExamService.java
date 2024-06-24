package com.alupit.ciit.spring_boot_state_management.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {

    // List to store questions and their corresponding options
    public List<String[]> questionsAndOptions = new ArrayList<>();

    // HashMap to store correct answers for each question
    public HashMap<String, String> answerKey = new HashMap<String, String>();

    // HashMap to store user responses for each question
    public HashMap<String, String> userResponses = new HashMap<String, String>();

    // Constructor to initialize questions, options, and answer key
    public ExamService() {
        // Initialize questions and options
        questionsAndOptions.add(new String[]{"What country has the highest life expectancy?", "Philippines", "Hong Kong", "USA", "Singapore"});
        questionsAndOptions.add(new String[]{"How many elements are in the periodic table?", "119", "118", "117", "120"});
        questionsAndOptions.add(new String[]{"Who was the Ancient Greek God of the Sun?", "Athena", "Hades", "Apollo", "Venus"});
        questionsAndOptions.add(new String[]{"What country drinks the most coffee per capita?", "Russia", "China", "USA", "Finland"});
        questionsAndOptions.add(new String[]{"What Renaissance artist is buried in Rome's Pantheon?", "Donatello", "Leonardo", "Michael", "Raphael"});

        // Initialize answer key with correct answers for each question
        answerKey.put("highLifeExpectancy","Hong Kong");
        answerKey.put("periodicTableElements","118");
        answerKey.put("sunGreekGod","Apollo");
        answerKey.put("coffeePerCapita","Finland");
        answerKey.put("romeRenaissanceArtist","Raphael");
    }

    // Method to check the exam based on user responses
    public int checkExam(HttpServletRequest request){
        int score = 0;

        // Retrieve user responses from request parameters and store them in userResponses HashMap
        userResponses.put("highLifeExpectancy",request.getParameter("what_country_has_the_highest_life_expectancy"));
        userResponses.put("periodicTableElements",request.getParameter("how_many_elements_are_in_the_periodic_table"));
        userResponses.put("sunGreekGod",request.getParameter("who_was_the_ancient_greek_god_of_the_sun"));
        userResponses.put("coffeePerCapita",request.getParameter("what_country_drinks_the_most_coffee_per_capita"));
        userResponses.put("romeRenaissanceArtist",request.getParameter("what_renaissance_artist_is_buried_in_rome's_pantheon"));

        // Iterate over each question in userResponses
        for (String question : userResponses.keySet()) {
            // Retrieve the answer provided by the user
            String userAnswer = userResponses.get(question);

            // Retrieve the correct answer from the answer key
            String correctAnswer = answerKey.get(question);

            // Check if the user's answer matches the correct answer
            if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                // Increment the score if the answers match
                score++;
            }
        }
        return score;
    }

    // Helper method to shuffle array elements from start index to end index
    public List<String[]> getQuestionsAndOptions() {
        // Iterate over each question and shuffle its options
        for (String[] questionAndOptions : questionsAndOptions) {
            // Convert array to list and shuffle the sublist containing options
            List<String> subList = Arrays.asList(questionAndOptions).subList(1, questionAndOptions.length);
            Collections.shuffle(subList);

            // Copy shuffled options back to the original array
            for (int i = 1; i <= questionAndOptions.length-1; i++) {
                questionAndOptions[i] = subList.get(i - 1);
            }
        }

        // Shuffle the entire list of questions and options
        Collections.shuffle(questionsAndOptions);

        // Return the shuffled list of questions and options
        return questionsAndOptions;
    }
}