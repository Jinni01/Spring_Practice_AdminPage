package com.midasin.spr.interceptor;

import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
    private Logger log = Logger.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println(request.getRequestURI());
        String logStr = request.getMethod() + " " + request.getRequestURI();
        String queryStr = request.getQueryString();
        if(queryStr != null)
            logStr = logStr + "?" + queryStr;
        if(!logStr.contains("resources"))
            log.debug(logStr);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }
}
