package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 员工编码类，继承自通用编码类
 * 用于确保员工编码的有效性和唯一性
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class EmployeeCode extends Code {
    /**
     * 构造一个新的员工编码实例
     *
     * @param value 员工编码的值，不能为空或仅包含空白字符
     *              这里使用断言来确保传入的编码值是有效的，以防止数据不一致
     */
    public EmployeeCode(String value) {
        super(value);
        Assert.hasText(value, "员工编码不能为空");
    }
}
