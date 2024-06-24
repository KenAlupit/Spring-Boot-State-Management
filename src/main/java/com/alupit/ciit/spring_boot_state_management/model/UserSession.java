package com.alupit.ciit.spring_boot_state_management.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    // Instance variable to store the username of the logged-in user
    private String username;

    // Getter method to retrieve the username
    public String getUsername() {
        return username;
    }

    // Setter method to set the username
    public void setUsername(String username) {
        this.username = username;
    }
}