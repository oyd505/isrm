package com.island.isrm.core.idaccess.domain.dp;

import lombok.Value;

/**
 * 员工联系方式类
 * 用于封装员工的联系方式信息，包括电话和邮箱
 * 通过使用Lombok的@Value注解，确保类的不可变性，适用于不会更改的对象
 * 这种设计确保了数据的封装性和一致性，防止外部直接修改对象状态
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Value
public class EmployeeContact {
    /**
     * 电话号码
     * 用于存储员工的电话联系方式
     */
    String phone;

    /**
     * 邮箱地址
     * 用于存储员工的电子邮件联系方式
     */
    String email;
}
