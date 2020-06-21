package ru.sberdyshev.geekbrains.java.javaspringsecond.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/login")
    public String showMyLoginPage() {
        log.debug("Called GET /login/");
        return "login";
    }

    @PostMapping("/login")
    public String showMyLoginPagePost() {
        log.debug("Called GET /login/");
        return "login";
    }
}
