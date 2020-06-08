package ru.sberdyshev.geekbrains.java.javaspringsecond.general.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MDCFilterConfiguration {

    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "traceId";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String requestHeader = null;

    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        final MDCFilter logMDCFilter = new MDCFilter(responseHeader, mdcTokenKey, requestHeader);
        registrationBean.setFilter(logMDCFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}