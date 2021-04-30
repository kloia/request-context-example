package com.kloia.multithread.service;

import com.kloia.multithread.configuration.RequestContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class ContextUtils {

    public static RequestContext getRequestContext() {
        try {
            return (RequestContext) RequestContextHolder.getRequestAttributes().getAttribute("requestContext", RequestAttributes.SCOPE_REQUEST);
        } catch (Exception e) {
            return null;
        }
    }

}
