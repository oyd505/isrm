package com.island.isrm.core.common.port.api;

import lombok.Data;

/**
 * 通用返回结果类，用于封装API响应数据
 * 该类使用了Lombok的@Data注解，自动生成getter和setter方法
 *
 * @param <T> 泛型，用于存放返回的数据类型
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Result<T> {
    // 状态码
    private String code;
    // 消息
    private String message;

    // 返回的数据
    private T data;

    /**
     * 构造方法，初始化状态码
     *
     * @param code 状态码
     */
    public Result(String code) {
        this.code = code;
    }

    /**
     * 创建成功的返回结果
     * 该方法用于当操作成功时返回数据，默认状态码为"200"
     *
     * @param data 返回的数据
     * @param <T>  泛型，与类的泛型一致
     * @return Result 成功的返回结果对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>("200");
        result.setData(data);
        return result;
    }

    /**
     * 创建失败的返回结果
     * 该方法用于当操作失败时返回错误信息，默认状态码为"500"
     *
     * @param message 错误信息
     * @param <T>     泛型，与类的泛型一致
     * @return Result 失败的返回结果对象
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>("500");
        result.setMessage(message);
        return result;
    }
}
