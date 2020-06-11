package com.tichalo.userauthentication.controller;

import com.tichalo.userauthentication.model.User;
import com.tichalo.userauthentication.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }


    @PostMapping()
    public String signupUser(@ModelAttribute("newUser")User user, Model model){
        String signUpError = null;

        if (userService.isUsernameAvailable(user.getUsername())){
            signUpError = "username already exists";
        }

        if (signUpError == null){
            if (userService.save(user) < 0){
                signUpError = "There was an error creating new user";
            }
        }

        if (signUpError != null){
            model.addAttribute("signUpError", signUpError);
        } else {
            model.addAttribute("singUpSuccess", true);
        }
        return "signup";
    }
}
