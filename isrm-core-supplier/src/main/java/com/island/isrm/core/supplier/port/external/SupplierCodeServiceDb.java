package com.island.isrm.core.supplier.port.external;

import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.external.SupplierCodeService;
import com.island.isrm.core.supplier.port.repo.dao.SupplierJpa;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 供应商编码服务数据库实现类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class SupplierCodeServiceDb implements SupplierCodeService {
    // 注入SupplierJpa用于访问数据库
    private final SupplierJpa supplierJpa;

    /**
     * 构造函数注入SupplierJpa
     *
     * @param supplierJpa SupplierJpa实例，用于与数据库交互
     */
    public SupplierCodeServiceDb(SupplierJpa supplierJpa) {
        this.supplierJpa = supplierJpa;
    }

    /**
     * 生成供应商编码
     * 该方法首先尝试从数据库中获取最大的供应商编码，如果存在，则在此基础上生成新的编码；
     * 如果不存在，则生成初始编码"S1000"。
     *
     * @return 新的供应商编码
     */
    @Override
    public SupplierCode generateSerialNumber() {
        // 尝试获取数据库中的最大供应商编码
        String maxSupplierCode = this.supplierJpa.findMaxSupplierCode();
        if (StringUtils.hasText(maxSupplierCode)) {
            // 如果最大编码存在，则解析其数字部分，加一后生成新的编码
            Integer maxSerialNumber = Integer.parseInt(maxSupplierCode.substring(1));
            return new SupplierCode(String.format("S%d", maxSerialNumber + 1));
        } else {
            // 如果最大编码不存在，则生成初始编码"S1000"
            return new SupplierCode("S1000");
        }
    }
}
