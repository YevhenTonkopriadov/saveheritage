package ua.gov.mkip.saveheritage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.gov.mkip.saveheritage.models.Role;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.servises.UserService;

import java.util.Collections;

@Controller
public class UserRegistrationController {

    @Autowired
    UserService userService;

    @GetMapping ("/registration")
    public String registration (Model model){
        model.addAttribute("title", "registration new user");
        return "registration";
    }

    @PostMapping ("/registration")
    public String registrationAddUser (Model model, User user) {
        model.addAttribute("title", "registration new user");
        if (user.getUsername().length()<2) {
            model.addAttribute("problem", "plese add user name");
            return "registration";
        }
        if (user.getPassword().length()<2) {
            model.addAttribute("problem", "plese add password");
            return "registration";
        }
        if (!user.getPassword().equals( user.getConfirmPassword())) {
            model.addAttribute("problem", "Password != ConfirmPassword");
            return "registration";
        }
        System.out.println(userService.findByUsername(user.getUsername()));
        if (userService.findByUsername(user.getUsername()) != null) {
            System.out.println();
            model.addAttribute("problem", "user is available");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        return "login";
    }
}
