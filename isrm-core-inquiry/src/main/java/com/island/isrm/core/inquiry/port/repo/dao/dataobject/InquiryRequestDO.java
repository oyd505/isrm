package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_request")
@EntityListeners(AuditingEntityListener.class)
public class InquiryRequestDO extends BaseDataObject {
    @Id
    @Column(nullable = false, length = 32)
    private String inquiryCode;
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    private String reason;
    private String participation;
    private String buyerCode;
    private String buyerName;
    @Column(length = 32)
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String paymentTerms;
    private String currency;
    private String purOrgCode;
    private String purOrgName;
}
