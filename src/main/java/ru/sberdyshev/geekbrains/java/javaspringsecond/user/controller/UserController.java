package ru.sberdyshev.geekbrains.java.javaspringsecond.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import java.util.UUID;

//todo change request mapping на get\post mapping
@Slf4j
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setOrderService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users/{user-id}")
    public String getOneUserById(@PathVariable(name = "user-id") UUID userId,
                                 Model model) {
        log.debug("Called GET /users/{user-id} with args: userId={}", userId);
        UserDto userDto = userService.getUser(userId);
        model.addAttribute("userDto", userDto);
        return "user-details";
    }

    @RequestMapping("/users/current-user")
    public String getOneUser(Model model) {
        log.debug("Called GET /users/current-user");
        UserDto userDto = userService.getCurrentUser();
        model.addAttribute("userDto", userDto);
        return "user-details";
    }
}
