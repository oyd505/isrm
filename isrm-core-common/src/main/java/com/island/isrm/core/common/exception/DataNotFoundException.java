package com.island.isrm.core.common.exception;

/**
 * 当数据未找到时抛出的异常类
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * 构造函数，初始化异常对象
     *
     * @param message 异常信息，描述数据未找到的情况
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
