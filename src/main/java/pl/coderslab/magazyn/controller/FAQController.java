package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {
    @GetMapping("/FAQ")
    public String faq(){
        return "FAQ";
    }
}
