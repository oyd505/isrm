package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 用户名类，继承自Code类
 * 用于验证用户名的合法性
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class UserName extends Code {
    /**
     * 构造函数，初始化用户名并进行合法性验证
     *
     * @param value 用户名字符串
     *              不能为空或全空白，否则会抛出IllegalArgumentException异常
     */
    public UserName(String value) {
        super(value);
        // 验证用户名是否为空或全空白
        Assert.hasText(value, "用户名不能为空");
    }
}
