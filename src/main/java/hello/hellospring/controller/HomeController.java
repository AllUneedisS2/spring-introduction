package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // index 보다 우선순위
    @GetMapping("/")
    public String home() {
        return "home";
    }

}