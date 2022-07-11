package ua.gov.mkip.craft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.craft.input.UserCraftsManRegistrationInput;
import ua.gov.mkip.craft.services.UserCraftsManService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserCraftsManService userCraftsManService;

    @RequestMapping("/registrationCraftsMan")
    public String registration(Model model) {
        model.addAttribute("title", "Реєстрація");
        model.addAttribute("userCraftsManRegistrationInput", new UserCraftsManRegistrationInput());
        return "registrationCraftsMan";
    }

    @PostMapping("/registrationCraftsMan")
    public String registrationAddUser(@ModelAttribute @Valid UserCraftsManRegistrationInput userCraftsManRegistrationInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "registrationCraftsMan";
        }
        userCraftsManService.save(userCraftsManRegistrationInput);
        return "login";
    }
}
