/**
 * 员工编码和名称接口
 * 用于获取员工的编码和名称信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.idaccess.port.repo.dao.projection;

public interface EmployeeCodeAndName {

    /**
     * 获取员工编码
     *
     * @return 员工编码字符串
     */
    String getCode();

    /**
     * 获取员工名称
     *
     * @return 员工名称字符串
     */
    String getName();
}
