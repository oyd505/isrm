package com.island.isrm.core.idaccess.domain.dp;

import com.island.isrm.core.common.domain.dp.Code;
import org.springframework.util.Assert;

/**
 * 商品编码类，继承自通用编码类Code
 * 用于确保商品编码的有效性和唯一性
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class ProductCode extends Code {
    /**
     * 构造函数，初始化商品编码值
     *
     * @param value 商品编码的字符串值
     *              不能为空或空字符串，否则会抛出IllegalArgumentException异常
     */
    public ProductCode(String value) {
        super(value);
        Assert.hasText(value, "商品编码不能为空"); // 验证商品编码不能为空
    }
}
