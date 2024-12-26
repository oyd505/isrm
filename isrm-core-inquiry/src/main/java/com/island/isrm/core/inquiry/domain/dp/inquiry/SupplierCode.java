package com.island.isrm.core.inquiry.domain.dp.inquiry;

import com.island.isrm.core.common.domain.dp.Code;

/**
 * 供应商编码类
 * 继承自Code类，用于标识和处理供应商相关的编码信息
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class SupplierCode extends Code {
    /**
     * 构造函数
     * 初始化供应商编码
     *
     * @param value 供应商编码的字符串值
     */
    public SupplierCode(String value) {
        super(value);
    }
}
