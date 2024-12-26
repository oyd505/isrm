package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

/**
 * 报价请求数据对象
 * 继承自BaseDataObject以获取基础的持久化功能
 * 使用Lombok的Data注解自动生成getter和setter方法
 * 使用Lombok的EqualsAndHashCode注解自动生成equals和hashCode方法，并包含所有父类属性
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "quote_request")
@EntityListeners(AuditingEntityListener.class)
public class QuoteRequestDO extends BaseDataObject {
    /**
     * 报价编码，作为主键
     */
    @Id
    private String quoteCode;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 报价金额
     */
    private BigDecimal amount;

    /**
     * 货币类型
     */
    private String currency;

    /**
     * 报价状态，长度限制为32字符
     */
    @Column(length = 32)
    private String status;

    /**
     * 询价编码
     */
    private String inquiryCode;
}
