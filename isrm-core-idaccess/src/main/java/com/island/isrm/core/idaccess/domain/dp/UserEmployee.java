package com.island.isrm.core.idaccess.domain.dp;

import lombok.Data;

/**
 * 用户员工信息类
 * 该类用于封装员工编码和名称，提供不可变的员工视图
 * 使用final修饰符确保了类的实例在创建后不可更改，从而增强了安全性
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class UserEmployee {
    /**
     * 员工编码
     */
    private final String code;

    /**
     * 员工名称
     */
    private final String name;
}
