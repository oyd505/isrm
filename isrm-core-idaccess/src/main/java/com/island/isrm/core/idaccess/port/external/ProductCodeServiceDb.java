package com.island.isrm.core.idaccess.port.external;

import com.island.isrm.core.idaccess.domain.dp.ProductCode;
import com.island.isrm.core.idaccess.domain.external.ProductCodeService;
import com.island.isrm.core.idaccess.port.repo.dao.ProductJpa;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品编码服务数据库实现类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class ProductCodeServiceDb implements ProductCodeService {

    // 注入ProductJpa用于访问数据库
    private final ProductJpa productJpa;

    /**
     * 构造函数注入ProductJpa
     *
     * @param productJpa ProductJpa实例，用于与数据库交互
     */
    public ProductCodeServiceDb(ProductJpa productJpa) {
        this.productJpa = productJpa;
    }

    /**
     * 生成商品编码
     * 该方法首先尝试从数据库中获取最大的商品编码，如果存在，则在此基础上生成新的编码；
     * 如果不存在，则生成初始编码"P1000"。
     *
     * @return 新的商品编码
     */
    @Override
    public ProductCode generateProductCode() {
        // 尝试获取数据库中的最大商品编码
        String maxProductCode = this.productJpa.findMaxProductCode();
        if (StringUtils.hasText(maxProductCode)) {
            // 如果最大编码存在，则解析其数字部分，加一后生成新的编码
            Integer maxSerialNumber = Integer.parseInt(maxProductCode.substring(1));
            return new ProductCode(String.format("P%d", maxSerialNumber + 1));
        } else {
            // 如果最大编码不存在，则生成初始编码"P1000"
            return new ProductCode("P1000");
        }
    }
} 