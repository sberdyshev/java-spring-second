package ru.sberdyshev.geekbrains.java.javaspringsecond.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage(HttpServletRequest request) {
        log.debug("Called GET /login/");
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("referrer", referrer);
        log.debug("Called GET /login/ with referrer={}", referrer);
        return "login";
    }

    @PostMapping("/login")
    public String showMyLoginPagePost(HttpServletRequest request) {
        log.debug("Called POST /login/");
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("referrer", referrer);
        log.debug("Called POST /login/ with referrer={}", referrer);
        return "login";
    }
}
