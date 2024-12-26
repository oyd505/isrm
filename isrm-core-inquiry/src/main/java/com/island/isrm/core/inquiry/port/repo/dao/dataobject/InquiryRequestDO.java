package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 询价请求数据对象
 * 继承自BaseDataObject以获取基础的持久化功能
 * 使用Lombok的Data和EqualsAndHashCode注解以自动生成getter、setter和equals方法
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_request")
@EntityListeners(AuditingEntityListener.class)
public class InquiryRequestDO extends BaseDataObject {
    /**
     * 询价编码，唯一标识符
     */
    @Id
    @Column(nullable = false, length = 32)
    private String inquiryCode;

    /**
     * 询价标题，简短描述询价内容
     */
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    /**
     * 询价原因，解释为何进行此次询价
     */
    private String reason;

    /**
     * 参与方式，描述供应商如何参与询价
     */
    private String participation;

    /**
     * 采购方编码，标识采购方
     */
    private String buyerCode;

    /**
     * 采购方名称，采购方的可读名称
     */
    private String buyerName;

    /**
     * 状态，表示询价请求当前的状态
     */
    @Column(length = 32)
    private String status;

    /**
     * 开始时间，询价开始的时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间，询价结束的时间
     */
    private LocalDateTime endTime;

    /**
     * 付款条件，描述付款的条款和条件
     */
    private String paymentTerms;

    /**
     * 货币，用于此次询价的货币类型
     */
    private String currency;

    /**
     * 采购组织编码，标识进行采购的组织
     */
    private String purOrgCode;

    /**
     * 采购组织名称，采购组织的可读名称
     */
    private String purOrgName;
}
