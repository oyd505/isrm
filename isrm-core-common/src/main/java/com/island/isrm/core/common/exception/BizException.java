package com.island.isrm.core.common.exception;

/**
 * 业务异常类
 * 继承自RuntimeException，用于在业务逻辑中标识和处理异常情况
 * 它允许在不必显式声明或捕获的情况下，抛出表示应用程序业务逻辑错误的异常
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class BizException extends RuntimeException {
    /**
     * 构造函数，接收一个字符串参数作为异常消息
     *
     * @param message 异常消息，描述发生的具体错误
     */
    public BizException(String message) {
        super(message);
    }
}
