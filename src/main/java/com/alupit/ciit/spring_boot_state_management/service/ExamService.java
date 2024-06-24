package com.alupit.ciit.spring_boot_state_management.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ExamService {

    public HashMap<String, String> answerKey = new HashMap<String, String>();

    public ExamService() {
        answerKey.put("highLifeExpectancy","Hong Kong");
        answerKey.put("periodicTableElements","118");
        answerKey.put("sunGreekGod","Apollo");
        answerKey.put("coffeePerCapita","Finland");
        answerKey.put("romeRenaissanceArtist","Raphael");
    }

    public int checkExam(HashMap<String, String> userResponses){
        int score = 0;
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
}