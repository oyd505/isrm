/**
 * 定义了一个接口，用于获取联系人姓名和电话信息
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.supplier.port.repo.dao.projection;

public interface ContactNameAndPhone {
    /**
     * 获取联系人姓名
     *
     * @return 联系人的姓名
     */
    String getName();

    /**
     * 获取联系人电话
     *
     * @return 联系人的电话号码
     */
    String getPhone();
}
