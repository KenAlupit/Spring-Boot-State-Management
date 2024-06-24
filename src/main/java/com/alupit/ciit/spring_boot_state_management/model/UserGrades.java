package com.alupit.ciit.spring_boot_state_management.model;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;

@Component
@SessionScope
public class UserGrades {
    private HashMap<String, Integer> userGrades = new HashMap<String, Integer>();

    public UserGrades(){
        userGrades.put("Admin", 0);
        userGrades.put("John", 0);
        userGrades.put("Mary", 0);
        userGrades.put("Peter", 0);
    }

    public void setUserGrade(String username, int grade){
        userGrades.put(username, grade);
    }

    public HashMap<String, Integer> getUserGrades(){
        return userGrades;
    }
}
