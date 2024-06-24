package com.alupit.ciit.spring_boot_state_management.service;

import com.alupit.ciit.spring_boot_state_management.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PasswordService {

    // HashMap containing user information: username as key, password as value
    public HashMap<String, String> UserInfo = new HashMap<>();

    @Autowired
    private UserSession userSession;

    public PasswordService(){
        // Add user information to the UserInfo HashMap
        UserInfo.put("Admin", "Password1");
        UserInfo.put("John", "Password2");
        UserInfo.put("Mary", "Password3");
        UserInfo.put("Peter", "Password4");
    }

    public boolean validateUser(String username, String password){
        if(password.equals(UserInfo.get(username))) {
            userSession.setUsername(username);
            return true;
        }
        return false;
    }
}