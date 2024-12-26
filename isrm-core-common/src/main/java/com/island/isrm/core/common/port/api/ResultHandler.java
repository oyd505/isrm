package com.island.isrm.core.common.port.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个方法，指示它需要特定的结果处理
 * 此注解必须应用于方法，并且在运行时可用，以便动态处理方法的执行结果
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultHandler {
}
