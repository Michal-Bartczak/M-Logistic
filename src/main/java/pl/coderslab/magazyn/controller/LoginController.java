package pl.coderslab.magazyn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.magazyn.generic.UserPasswordEncryptor;
import pl.coderslab.magazyn.service.CustomUserDetailsService;
import pl.coderslab.magazyn.service.UserService;

@Controller
public class LoginController {
    private final CustomUserDetailsService customUserDetailsService;

    private final UserService userService;
    private final UserPasswordEncryptor userPasswordEncryptor;

    public LoginController(CustomUserDetailsService customUserDetailsService, UserService userService, UserPasswordEncryptor userPasswordEncryptor) {
        this.customUserDetailsService = customUserDetailsService;

        this.userService = userService;
        this.userPasswordEncryptor = userPasswordEncryptor;
    }

    @GetMapping("/login")
    public String login() {
        return "homepages/login";
    }


}