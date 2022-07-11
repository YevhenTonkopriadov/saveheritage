package ua.gov.mkip.craft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.services.UserCraftsManService;
import ua.gov.mkip.craft.services.UserService;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

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

    @RequestMapping("/user/{id}")
    public String userviewer(Model model, @PathVariable("id") Long id) {
        Optional<UserCraftsMan> userCraftsMan = userCraftsManService.findById(id);
        if (userCraftsMan.isPresent()) {
            model.addAttribute("craftMan", userCraftsMan.get());
            return "userviewer";
        } else {
            return "index";
        }
    }
}
