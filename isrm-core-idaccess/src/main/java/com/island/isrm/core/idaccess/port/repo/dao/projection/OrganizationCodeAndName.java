package com.island.isrm.core.idaccess.port.repo.dao.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 组织编码和名称类
 * 用于存储组织的编码和名称信息
 * 主要作用是作为一个数据传输对象（DTO），用于在不同层次或模块之间传递组织的编码和名称信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Data
@AllArgsConstructor
public class OrganizationCodeAndName {
    /**
     * 组织编码
     * 用于唯一标识一个组织
     */
    private String code;

    /**
     * 组织名称
     * 用于表示组织的全称
     */
    private String name;
}
