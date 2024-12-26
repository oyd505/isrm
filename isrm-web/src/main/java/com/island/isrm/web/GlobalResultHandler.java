package com.island.isrm.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.island.isrm.core.common.port.api.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应处理器
 * 该类用于统一处理所有控制器的响应体，使其符合统一的格式
 * 实现了ResponseBodyAdvice接口，可以对响应体进行预处理
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@RestControllerAdvice
public class GlobalResultHandler implements ResponseBodyAdvice<Object> {

    /**
     * ObjectMapper用于JSON序列化和反序列化
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 判断是否支持当前的返回类型和消息转换器
     *
     * @param returnType    返回值类型
     * @param converterType 消息转换器类型
     * @return 总是返回true，表示支持所有返回类型和消息转换器
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在响应体写入前进行处理
     * 如果响应体是String类型，则使用ObjectMapper转换为JSON格式
     * 如果响应体是Result类型，则直接返回
     * 否则，将响应体包装为Result类型并返回
     *
     * @param body                  响应体
     * @param returnType            返回值类型
     * @param selectedContentType   选择的内容类型
     * @param selectedConverterType 选择的消息转换器类型
     * @param request               请求
     * @param response              响应
     * @return 处理后的响应体
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            // 当响应体是String类型时，使用ObjectMapper转换，因为Spring默认使用StringHttpMessageConverter处理字符串，不会将字符串识别为JSON
            return objectMapper.writeValueAsString(Result.success(body));
        }
        // 如果响应体是Result类型，直接返回；否则，将响应体包装为Result类型并返回
        return body instanceof Result ? body : Result.success(body);
    }
}
