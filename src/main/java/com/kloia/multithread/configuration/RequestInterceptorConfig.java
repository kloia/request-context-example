package com.kloia.multithread.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Slf4j
@Configuration
public class RequestInterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getRequestInterceptor()).addPathPatterns("/**");
    }

    @Bean
    @RequestScope
    public RequestContext requestContext() {
        return new RequestContext();
    }

}