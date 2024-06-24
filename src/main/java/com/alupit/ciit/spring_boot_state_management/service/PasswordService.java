package com.alupit.ciit.spring_boot_state_management.service;

import com.alupit.ciit.spring_boot_state_management.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class PasswordService {

    // HashMap containing user information: username as key, password as value
    public HashMap<String, String> UserInfo = new HashMap<>();

    // Autowired UserSession to manage user session information
    @Autowired
    private UserSession userSession;

    // Constructor to initialize user information
    public PasswordService(){
        // Initialize UserInfo HashMap with predefined username-password pairs
        UserInfo.put("Admin", "Password1");
        UserInfo.put("John", "Password2");
        UserInfo.put("Mary", "Password3");
        UserInfo.put("Peter", "Password4");
    }

    // Method to validate user credentials
    public boolean validateUser(String username, String password){
        // Check if the provided password matches the stored password for the username
        if(password.equals(UserInfo.get(username))) {
            // Set the username in the UserSession instance upon successful validation
            userSession.setUsername(username);
            return true; // Return true if validation is successful
        }
        return false; // Return false if validation fails
    }
}