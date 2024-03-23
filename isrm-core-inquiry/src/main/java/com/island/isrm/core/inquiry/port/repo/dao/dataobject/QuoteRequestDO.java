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
@Table(name = "quote_request")
@EntityListeners(AuditingEntityListener.class)
public class QuoteRequestDO extends BaseDataObject {
    @Id
    private String quoteCode;
    private String supplierCode;
    private String supplierName;
    private BigDecimal amount;
    private String currency;
    @Column(length = 32)
    private String status;
    private String inquiryCode;
}
