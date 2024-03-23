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

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleException(HttpServletRequest req, Authentication authentication, AccessDeniedException e) {
        log.error("{} 没有权限访问: {}", authentication.getName(), req.getServletPath());
        return Result.fail("没有权限访问");
    }

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

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleException(ConstraintViolationException cve) {
        StackTraceElement stackTraceElement = Arrays.stream(cve.getStackTrace()).filter(p -> p.getClassName().startsWith("com.island.isrm")).findFirst().get();
        log.error("{}.{} 入参错误: {}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), cve.getMessage());
        return Result.fail(cve.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public Result<String> handleException(BizException e) {
        StackTraceElement stackTraceElement = Arrays.stream(e.getStackTrace()).filter(p -> p.getClassName().startsWith("com.island.isrm")).findFirst().get();
        log.error("{}.{} 业务逻辑错误: {}", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.fail(e.getMessage());
    }
}
