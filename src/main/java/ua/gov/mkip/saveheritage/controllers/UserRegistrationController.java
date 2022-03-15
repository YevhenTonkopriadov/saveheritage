package ua.gov.mkip.saveheritage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.saveheritage.input.UserRegistrationInput;
import ua.gov.mkip.saveheritage.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @RequestMapping ("/registration")
    public String registration (Model model){
        model.addAttribute("title","Реєстрація");
        model.addAttribute("userRegistrationInput", new UserRegistrationInput());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationAddUser (@ModelAttribute @Valid UserRegistrationInput userRegistrationInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userRegistrationInput);
        return "login";
    }
}
