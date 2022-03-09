package ua.gov.mkip.saveheritage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.saveheritage.models.Role;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.saveheritage.validator.UserValidator;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    @RequestMapping ("/adduser")
    public String registration (Model model){
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/registration")
    public String registrationAddUser (@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user,bindingResult);
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "login";
    }
}
