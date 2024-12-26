package com.island.isrm.core.supplier.domain.dp;

import lombok.Data;

/**
 * 供应商的联系人
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
public class Contact {
    /**
     * 姓名
     */
    private final String name;

    /**
     * 电话号码
     */
    private final String phone;
}
