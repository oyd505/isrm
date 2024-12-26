package com.island.isrm.core.idaccess.domain.dp;

import lombok.Value;

/**
 * Department类代表一个部门对象，封装了部门的基本信息，如部门编码和名称
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Value
public class Department {
    /**
     * 部门编码，用于唯一标识一个部门
     */
    String code;

    /**
     * 部门名称，用于显示部门的全称
     */
    String name;
}
