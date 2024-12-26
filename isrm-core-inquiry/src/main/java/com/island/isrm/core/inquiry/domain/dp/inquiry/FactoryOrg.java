package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

/**
 * 工厂组织类，用于封装工厂组织的基本信息。
 * 该类包含工厂组织的编码和名称，提供了一种简单的方式来标识和管理工厂组织。
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class FactoryOrg {
    /**
     * 工厂组织的唯一编码，用于系统识别和查询。
     */
    private final String code;

    /**
     * 工厂组织的名称，用于显示和用户识别。
     */
    private final String name;
}
