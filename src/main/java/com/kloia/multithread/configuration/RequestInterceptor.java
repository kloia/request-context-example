package com.kloia.multithread.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Resource(name = "requestContext")
    private RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestContext.setId(request.getHeader("userId"));
        requestContext.setDate(new Date());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        requestAttributes.setAttribute("requestContext", requestContext, RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.setRequestAttributes(requestAttributes, true);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        RequestContextHolder.resetRequestAttributes();
    }
}
