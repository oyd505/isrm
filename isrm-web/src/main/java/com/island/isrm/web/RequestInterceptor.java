package com.island.isrm.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Slf4j
@RestControllerAdvice
public class RequestInterceptor implements RequestBodyAdvice {

    // 是否开启拦截 true开启 false不开启
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    //请求体解析前处理
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return httpInputMessage;
    }

    //请求体解析后处理
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求{}.{}({})", method.getDeclaringClass().getSimpleName(), method.getName(), body);
        return body;
    }

    //处理没有参数
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求{}.{}()", method.getDeclaringClass().getSimpleName(), method.getName());
        return body;
    }

}
