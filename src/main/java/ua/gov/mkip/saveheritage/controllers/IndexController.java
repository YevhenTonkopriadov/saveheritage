package ua.gov.mkip.saveheritage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("title","Головна");
        return "index";
    }
    @GetMapping("/home")
    public String home (Model model) {
        model.addAttribute("title","Домашня");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
       model.addAttribute("title","Авторизація");
        return "login";
    }
}
