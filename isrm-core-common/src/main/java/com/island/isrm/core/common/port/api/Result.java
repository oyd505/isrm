package com.island.isrm.core.common.port.api;

import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String message;

    private T data;

    public Result(String code) {
        this.code = code;
    }

    /**
     * @param data data
     * @param <T>  不支持String类型
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>("200");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>("500");
        result.setMessage(message);
        return result;
    }
}
