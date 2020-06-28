package ru.sberdyshev.geekbrains.java.javaspringsecond.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

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

    @GetMapping("/registration")
    public String getRegistrationPage(HttpServletRequest request) {
        log.info("Called GET /registration - getRegistrationPage() - Start");
        log.info("Called GET /registration - getRegistrationPage() - Finished");
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam(value = "username") String userName,
                           @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "matchingPassword") String matchingPassword,
                           HttpServletRequest request) {
        log.info("Called GET /registration - register() - Start");
        log.info("Called GET /registration - register() - args: username={}, firstName={}, lastName={}, password={}, matchingPassword={}",
                userName,
                firstName,
                lastName,
                password,
                matchingPassword);
        UserDto userDto = new UserDto(null, userName, password, firstName, lastName, matchingPassword, null);
        Optional<UserDto> optionalUserDto = Optional.of(userDto);
        userService.registerUser(optionalUserDto);
        log.info("Called GET /registration - register() - Finished");
        //todo redirect на логин?
        return "login";
    }
}
