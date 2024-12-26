package com.island.isrm.core.inquiry.port.repo.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 供应商商品询价单数据传输对象
 * 用于封装供应商及其商品信息，以便在系统间传递数据
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class InquirySupplierProducts {
    /**
     * 询价单编码
     * 用于唯一标识一个询价单
     */
    private String inquiryCode;
    /**
     * 询价单状态
     * 描述询价单当前所处的状态，如待确认、已确认等
     */
    private String status;
    /**
     * 供应商信息
     * 包含供应商的详细信息，如供应商编码、名称等
     */
    private Supplier supplier;
    /**
     * 商品列表
     * 包含该供应商下的所有商品信息，每个商品包括编码、名称、数量等
     */
    private List<Product> products;

    /**
     * 供应商类
     * 用于封装供应商的相关信息
     */
    @Data
    public static class Supplier {
        /**
         * 供应商编码
         * 用于唯一标识一个供应商
         */
        private String code;
        /**
         * 供应商名称
         * 描述供应商的名称
         */
        private String name;
        /**
         * 是否已确认
         * 标识供应商是否已确认询价单
         */
        private String confirmed;
        /**
         * 是否已审核
         * 标识供应商信息是否已通过审核
         */
        private String audited;
    }

    /**
     * 商品类
     * 用于封装商品相关信息
     */
    @Data
    public static class Product {
        /**
         * 商品编码
         * 用于唯一标识一个商品
         */
        private String code;
        /**
         * 商品名称
         * 描述商品的名称
         */
        private String name;
        /**
         * 商品数量
         * 表示该商品在询价单中的数量
         */
        private BigDecimal quantity;
        /**
         * 行号
         * 用于标识商品在询价单中的顺序号
         */
        private Integer lineNumber;
    }
}
