package com.cn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by home on 2017/6/27.
 */
public abstract  class  BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();


    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
        currentRequest.set(request);
        currentResponse.set(response);
    }

    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }


    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }



}
