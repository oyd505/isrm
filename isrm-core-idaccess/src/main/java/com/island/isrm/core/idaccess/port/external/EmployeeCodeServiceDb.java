package com.island.isrm.core.idaccess.port.external;

import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.external.EmployeeCodeService;
import com.island.isrm.core.idaccess.port.repo.dao.EmployeeJpa;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 员工编码服务数据库实现类
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class EmployeeCodeServiceDb implements EmployeeCodeService {

    // 注入EmployeeJpa用于访问数据库
    private final EmployeeJpa employeeJpa;

    /**
     * 构造函数注入EmployeeJpa
     *
     * @param employeeJpa EmployeeJpa实例，用于与数据库交互
     */
    public EmployeeCodeServiceDb(EmployeeJpa employeeJpa) {
        this.employeeJpa = employeeJpa;
    }

    /**
     * 生成员工编码
     * 该方法首先尝试从数据库中获取最大的员工编码，如果存在，则在此基础上生成新的编码；
     * 如果不存在，则生成初始编码"E1000"。
     *
     * @return 新的员工编码
     */
    @Override
    public EmployeeCode generateSerialNumber() {
        // 尝试获取数据库中的最大员工编码
        String maxEmployeeCode = this.employeeJpa.findMaxEmployeeCode();
        if (StringUtils.hasText(maxEmployeeCode)) {
            // 如果最大编码存在，则解析其数字部分，加一后生成新的编码
            Integer maxSerialNumber = Integer.parseInt(maxEmployeeCode.substring(1));
            return new EmployeeCode(String.format("E%d", maxSerialNumber + 1));
        } else {
            // 如果最大编码不存在，则生成初始编码"E1000"
            return new EmployeeCode("E1000");
        }
    }
}
