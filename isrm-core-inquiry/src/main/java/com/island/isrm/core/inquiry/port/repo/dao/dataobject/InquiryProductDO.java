package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

/**
 * 询价商品数据对象
 * 继承自BaseDataObject以获取基础的实体属性和行为
 * 使用Lombok的Data注解自动生成getter和setter，减少boilerplate编码
 * 使用EqualsAndHashCode注解来自动生成equals和hashCode方法，提高对象比较和散列集合的性能
 * 使用JPA注解来映射到数据库表
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_product")
@EntityListeners(AuditingEntityListener.class)
public class InquiryProductDO extends BaseDataObject {
    /**
     * 主键ID，由数据库自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 询价编码，不能为空，用于关联询价单
     */
    @Column(nullable = false)
    private String inquiryCode;

    /**
     * 商品编码，可能为空，用于标识特定商品
     */
    private String code;

    /**
     * 商品名称，可能为空，用于描述商品
     */
    private String name;

    /**
     * 数量，可能为空，表示询价商品的数量
     */
    private BigDecimal quantity;

    /**
     * 单位，可能为空，表示商品的计量单位
     */
    private String units;

    /**
     * 工厂编码，可能为空，用于标识生产工厂
     */
    private String factoryCode;

    /**
     * 工厂名称，可能为空，用于描述生产工厂的名称
     */
    private String factoryName;

    /**
     * 交货日期，可能为空，表示期望的交货日期
     */
    private String deliveryDate;

    /**
     * 行号，可能为空，用于在询价单中排序商品行
     */
    private Integer lineNumber;
}
