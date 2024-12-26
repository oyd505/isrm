package com.island.isrm.core.idaccess.domain.external;

import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;

/**
 * 员工编码服务接口
 * <p>
 * 该接口定义了一个方法，用于生成员工的唯一编码
 * 唯一编码对于区分不同员工至关重要
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface EmployeeCodeService {
    /**
     * 生成员工的唯一编码
     *
     * @return 唯一的员工编码对象
     */
    EmployeeCode generateSerialNumber();
}
