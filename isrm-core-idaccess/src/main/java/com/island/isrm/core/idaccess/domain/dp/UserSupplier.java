package com.island.isrm.core.idaccess.domain.dp;

import lombok.Data;

/**
 * 用户供应商类，用于标识用户信息的来源
 * 本类通过final关键字确保了编码和name属性的不可变性，体现了用户供应商编码和名称的固定性
 * 使用@Data注解自动生成getter和setter方法，简化了属性访问的模板编码
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class UserSupplier {
    /**
     * 用户供应商编码，唯一标识供应商
     */
    private final String code;
    /**
     * 用户供应商名称，描述供应商的名称
     */
    private final String name;
}
