package com.island.isrm.core.idaccess.port.repo.dao.projection;

/**
 * 商品编码和名称接口
 * 提供获取商品编码和名称的方法
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public interface ProductCodeAndName {

    /**
     * 获取商品编码
     *
     * @return 商品编码的字符串表示
     */
    String getCode();

    /**
     * 获取商品名称
     *
     * @return 商品名称的字符串表示
     */
    String getName();
}
