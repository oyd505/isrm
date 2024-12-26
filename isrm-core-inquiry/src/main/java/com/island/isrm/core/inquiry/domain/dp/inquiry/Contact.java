package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

/**
 * 联系人
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Contact {
    /**
     * 名字
     */
    private final String name;
    
    /**
     * 电话
     */
    private final String phone;
}
