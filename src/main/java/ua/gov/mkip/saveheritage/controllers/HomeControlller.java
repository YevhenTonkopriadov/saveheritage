package ua.gov.mkip.saveheritage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlller {

    @GetMapping("/home")
    public String home (Model model) {
        model.addAttribute("title", "save haretage");
        return "home";
    }
}
