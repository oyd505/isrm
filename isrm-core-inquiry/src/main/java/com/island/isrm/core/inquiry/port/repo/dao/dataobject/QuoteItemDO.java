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
@Table(name = "quote_item")
@EntityListeners(AuditingEntityListener.class)
public class QuoteItemDO extends BaseDataObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer inquiryLine;
    private String productCode;
    private String productName;
    private BigDecimal quoteQuantity;
    private String units;
    private String deliveryDeadline;
    private BigDecimal price;
    private BigDecimal taxRate;
    @Column(nullable = false)
    private String quoteCode;
}
