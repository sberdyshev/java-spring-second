package ru.sberdyshev.geekbrains.java.javaspringsecond.general.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class Slf4jMDCFilter extends OncePerRequestFilter {

    private final String responseHeader;
    private final String mdcTokenKey;
    private final String requestHeader;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws java.io.IOException, ServletException {
        try {
            final String token;
            if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
                token = request.getHeader(requestHeader);
            } else {
                token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
            }
            MDC.put(mdcTokenKey, token);
            MDC.put("user", SecurityContextHolder.getContext().getAuthentication().getName());
            MDC.put("session", RequestContextHolder.currentRequestAttributes().getSessionId());
            if (!StringUtils.isEmpty(responseHeader)) {
                response.addHeader(responseHeader, token);
            }
            chain.doFilter(request, response);
        } finally {
            MDC.remove(mdcTokenKey);
        }
    }
}
