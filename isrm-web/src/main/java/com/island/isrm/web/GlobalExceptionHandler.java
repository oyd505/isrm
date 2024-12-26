package com.island.isrm.web;

import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.common.port.api.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 * 用于统一处理各类异常，确保异常信息以统一格式返回给前端
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理访问权限异常
     * 当用户没有足够权限访问某个资源时抛出
     *
     * @param req            HTTP请求对象，用于获取请求路径信息
     * @param authentication 认证信息，用于获取用户信息
     * @param e              访问权限异常对象
     * @return 返回表示失败结果，包含错误信息
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleException(HttpServletRequest req, Authentication authentication, AccessDeniedException e) {
        log.error("{} 没有权限访问: {}", authentication.getName(), req.getServletPath());
        return Result.fail("没有权限访问");
    }

    /**
     * 处理方法参数验证异常
     * 当方法参数验证失败时抛出
     *
     * @param e 方法参数验证异常对象
     * @return 返回表示失败结果，包含验证错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleException(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        result.getFieldErrors().forEach((item) -> {
            String message = item.getDefaultMessage();
            String field = item.getField();
            map.put(field, message);
        });
        log.error("接口数据错误", e);
        return Result.fail(map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&")));
    }

    /**
     * 处理约束验证异常
     * 当数据约束验证失败时抛出
     *
     * @param cve 约束验证异常对象
     * @return 返回表示失败结果，包含验证错误信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleException(ConstraintViolationException cve) {
        StackTraceElement stackTraceElement = Arrays.stream(cve.getStackTrace()).filter(p -> p.getClassName().startsWith("com.island.isrm")).findFirst().get();
        log.error("{}.{} 入参错误: {}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), cve.getMessage());
        return Result.fail(cve.getMessage());
    }

    /**
     * 处理业务逻辑异常
     * 当业务逻辑处理过程中出现错误时抛出
     *
     * @param e 业务逻辑异常对象
     * @return 返回表示失败结果，包含错误信息
     */
    @ExceptionHandler(BizException.class)
    public Result<String> handleException(BizException e) {
        StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(p -> p.getClassName().startsWith("com.island.isrm")).findFirst().get();
        log.error("{}.{} 业务逻辑错误: {}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 处理系统异常
     * 当发生未被其他异常处理器捕获的异常时抛出
     *
     * @param e 系统异常对象
     * @return 返回表示失败结果，包含错误信息
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.fail(e.getMessage());
    }
}
