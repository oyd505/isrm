package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

/**
 * 报价单项目数据对象
 * 继承自BaseDataObject以获取基础的实体属性和审计功能
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "quote_item")
@EntityListeners(AuditingEntityListener.class)
public class QuoteItemDO extends BaseDataObject {
    /**
     * 主键ID，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 询价行号
     */
    private Integer inquiryLine;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 报价数量
     */
    private BigDecimal quoteQuantity;

    /**
     * 单位
     */
    private String units;

    /**
     * 交货截止日期
     */
    private String deliveryDeadline;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 报价单编码，非空字段
     */
    @Column(nullable = false)
    private String quoteCode;
}
