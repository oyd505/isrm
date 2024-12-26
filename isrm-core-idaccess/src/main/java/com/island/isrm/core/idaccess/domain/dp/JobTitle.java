package com.island.isrm.core.idaccess.domain.dp;

import lombok.Value;

/**
 * 职称领域对象
 * 用于表示系统中的职称信息，如“经理”、“工程师”等
 * 通过使用 Lombok 的 @Value 注解，确保该类是不可变的，提供更好的线程安全
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Value
public class JobTitle {
    /**
     * 职称的值
     * 使用 String 类型来保存职称的名称，如“经理”、“工程师”等
     */
    String value;
}
