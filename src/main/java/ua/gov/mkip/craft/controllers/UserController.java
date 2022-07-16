package ua.gov.mkip.craft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.gov.mkip.craft.input.UserCraftsManRegistrationInput;
import ua.gov.mkip.craft.input.UserMkipRegistrationInput;
import ua.gov.mkip.craft.input.UserPasswordInput;
import ua.gov.mkip.craft.input.UserRegistrationInput;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.services.UserCraftsManService;
import ua.gov.mkip.craft.services.UserService;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserCraftsManService userCraftsManService;

    @RequestMapping("/listOfUser")
    public String listOfUser(Model model) {
        model.addAttribute("listOfUser", userService.findAll());
        return "listOfUser";
    }
    @RequestMapping("/cabinet")
    public String cabinet (Model model) throws UnsupportedEncodingException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userCraftsMan", userCraftsManService.findByUsername(user.getUsername()));
        return "cabinet";
    }

    @RequestMapping("/userveiwer/{id}")
    public String userviewer(Model model, @PathVariable("id") Long id) {
        Optional<UserCraftsMan> userCraftsMan = userCraftsManService.findById(id);
        if (userCraftsMan.isPresent()) {
            model.addAttribute("craftMan", userCraftsMan.get());
            return "userviewer";
        } else {
            return "home";
        }
    }

/* Registration Crafts Man */

    @RequestMapping("/registrationCraftsMan")
    public String registration(Model model) {
        model.addAttribute("title", "Реєстрація");
        model.addAttribute("userCraftsManRegistrationInput", new UserCraftsManRegistrationInput());
        return "registrationCraftsMan";
    }

    @PostMapping("/registrationCraftsMan")
    public String registrationAddUser(@ModelAttribute @Valid UserCraftsManRegistrationInput userCraftsManRegistrationInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationCraftsMan";
        }
        userCraftsManService.save(userCraftsManRegistrationInput);
        return "login";
    }

    /* administrator functions */

    @RequestMapping ("/setRole/{userRole}/{id}")
    public String setRoleUser (Model model, @PathVariable ("userRole") Role userRole, @PathVariable("id")Long userId){
        userService.setUserRole(userId,userRole);
        model.addAttribute("listOfUser", userService.findAll());
        return "listOfUser";
    }

    @RequestMapping ("/addUser")
    public String addUser (Model model){
        model.addAttribute("title", "створити користувача");
        return "addUser";
    }
    @PostMapping  ("/addUserByRole")
    public String addUserByRole (Model model, @ModelAttribute Role role ) {
        model.addAttribute("title", "створити користувача " + role.getDisplayValue());
        switch (role) {
            case USERREGISTERS -> {
                UserCraftsManRegistrationInput userCraftsManRegistrationInput = new UserCraftsManRegistrationInput();
                userCraftsManRegistrationInput.setRoles(Set.of(Role.USERREGISTERS));
                model.addAttribute("userCraftsManRegistrationInput", userCraftsManRegistrationInput);
                return "registrationCraftsMan";
            }
            case  USERADOPED-> {
                UserCraftsManRegistrationInput userCraftsManRegistrationInput = new UserCraftsManRegistrationInput();
                userCraftsManRegistrationInput.setRoles(Set.of(Role.USERADOPED));
                model.addAttribute("userCraftsManRegistrationInput", userCraftsManRegistrationInput);
                return "registrationCraftsMan";
            }
            case  USER -> {
                UserCraftsManRegistrationInput userCraftsManRegistrationInput = new UserCraftsManRegistrationInput();
                userCraftsManRegistrationInput.setRoles(Set.of(Role.USER));
                model.addAttribute("userCraftsManRegistrationInput", userCraftsManRegistrationInput);
                return "registrationCraftsMan";
            }
            case ADMIN -> {
                model.addAttribute("userRegistrationInput", new UserRegistrationInput());
                return "registrationUserAdmin";
            }
            case MKIP -> {
                model.addAttribute("userMkipRegistrationInput", new UserMkipRegistrationInput());
                return "registrationUserMkip";
            }
        }
        return "home";
    }

    @PostMapping("/registrationUserAdmin")
    public String registrationUserAdmin (@ModelAttribute @Valid UserRegistrationInput userRegistrationInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationUserAdmin";
        }
        userService.save(userRegistrationInput);
        return "home";
    }

    @PostMapping("/registrationUserMkip")
    public String registrationUserMkip (@ModelAttribute @Valid UserMkipRegistrationInput userMkipRegistrationInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationUserMkip";
        }
        userService.save(userMkipRegistrationInput);
        return "home";
    }

    @RequestMapping("/deleteUser/{userId}")
    public String deleteUser (Model model, @PathVariable ("userId") Long userId) {
        userService.delete(userId);
        model.addAttribute("listOfUser", userService.findAll());
        return "listOfUser";
    }

    @RequestMapping("/setNewPassword/{userId}")
    public String setNewPassword (Model model, @PathVariable ("userId") Long userId) {
        model.addAttribute("userPasswordInput", new UserPasswordInput(userId));
        return "setNewPassword";
    }
    @PostMapping("/setNewPassword")
    public String setNewPassword(@ModelAttribute @Valid UserPasswordInput userPasswordInput, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "setNewPassword";
        }
        userService.setUserPassword (userPasswordInput);
        return "home";
    }

}
