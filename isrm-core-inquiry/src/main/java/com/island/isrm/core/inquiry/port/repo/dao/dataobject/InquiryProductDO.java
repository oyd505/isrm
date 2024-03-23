package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_product")
@EntityListeners(AuditingEntityListener.class)
public class InquiryProductDO extends BaseDataObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String inquiryCode;
    private String code;
    private String name;
    private BigDecimal quantity;
    private String units;
    private String factoryCode;
    private String factoryName;
    private String deliveryDate;
    private Integer lineNumber;
}
