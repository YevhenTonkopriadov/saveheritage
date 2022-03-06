package ua.gov.mkip.saveheritage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index () {
        return "index";
    }
    @GetMapping("/home")
    public String home () {
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
