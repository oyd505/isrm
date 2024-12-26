package com.island.isrm.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 请求拦截器，用于日志记录和请求体的预处理
 * 实现了RequestBodyAdvice接口，以拦截和处理请求体
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Slf4j
@RestControllerAdvice
public class RequestInterceptor implements RequestBodyAdvice {

    /**
     * 判断是否支持当前的请求体处理
     *
     * @param methodParameter 方法参数
     * @param type            类型
     * @param aClass          HttpMessageConverter的类
     * @return true表示支持，false表示不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 在请求体解析前进行处理
     *
     * @param httpInputMessage Http输入消息
     * @param methodParameter  方法参数
     * @param type             类型
     * @param aClass           HttpMessageConverter的类
     * @return 处理后的Http输入消息
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return httpInputMessage;
    }

    /**
     * 在请求体解析后进行处理
     *
     * @param body             请求体
     * @param httpInputMessage Http输入消息
     * @param methodParameter  方法参数
     * @param type             类型
     * @param aClass           HttpMessageConverter的类
     * @return 处理后的请求体
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求{}.{}({})", method.getDeclaringClass().getSimpleName(), method.getName(), body);
        return body;
    }

    /**
     * 处理空请求体的情况
     *
     * @param body             请求体
     * @param httpInputMessage Http输入消息
     * @param methodParameter  方法参数
     * @param type             类型
     * @param aClass           HttpMessageConverter的类
     * @return 处理后的请求体
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求{}.{}()", method.getDeclaringClass().getSimpleName(), method.getName());
        return body;
    }

}
