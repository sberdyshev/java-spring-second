package ru.sberdyshev.geekbrains.java.javaspringsecond.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.dto.UserDto;
import ru.sberdyshev.geekbrains.java.javaspringsecond.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private UserService userService;

    public CustomAuthenticationSuccessHandler() {
        super();
        setUseReferer(true);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.debug("onAuthenticationSuccess() - Start");
        String userName = authentication.getName();
        UserDto userDto = userService.getUser(userName);
        HttpSession session = request.getSession();
        session.setAttribute("user", userDto);
        DefaultSavedRequest springSecuritySavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (springSecuritySavedRequest != null && !springSecuritySavedRequest.getRedirectUrl().contains("registration")) {
            log.debug("onAuthenticationSuccess() -  using springSecuritySavedRequest={}", springSecuritySavedRequest);
            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            String referrer = (String) request.getSession().getAttribute("referrer");
            if (referrer == null || referrer.contains("registration")) {
                referrer = "/products";
            }
            log.debug("onAuthenticationSuccess() -  using referrer={}", referrer);
            getRedirectStrategy().sendRedirect(request, response, referrer);
        }
        log.debug("onAuthenticationSuccess() - Finish");
    }
}
