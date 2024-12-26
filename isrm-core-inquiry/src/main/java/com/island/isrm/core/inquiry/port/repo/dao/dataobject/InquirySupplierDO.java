package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 询价供应商数据对象
 * 继承自BaseDataObject以获取基础的持久化功能
 * 使用Lombok的@Data和@EqualsAndHashCode注解以自动生成getter、setter和equals方法
 * 使用Spring Data JPA的@EntityListeners注解以启用审计功能
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_supplier")
@EntityListeners(AuditingEntityListener.class)
public class InquirySupplierDO extends BaseDataObject {
    /**
     * 主键ID，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 询价编码，不能为空
     */
    @Column(nullable = false)
    private String inquiryCode;

    /**
     * 供应商编码
     */
    private String code;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 是否确认，默认为"N"
     */
    @Column(length = 1)
    private String confirmed = "N";

    /**
     * 是否审核，默认为"N"
     */
    @Column(length = 1)
    private String audited = "N";

    /**
     * 报价次数，默认为0
     */
    private Integer quoteCount = 0;

    /**
     * 报价编码
     */
    private String quoteCode;
}
