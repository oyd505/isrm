package com.island.isrm.core.inquiry.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "inquiry_supplier")
@EntityListeners(AuditingEntityListener.class)
public class InquirySupplierDO extends BaseDataObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String inquiryCode;
    private String code;
    private String name;
    private String contactName;
    private String contactPhone;
    @Column(length = 1)
    private String confirmed = "N";
    @Column(length = 1)
    private String audited = "N";
    private Integer quoteCount = 0;
    private String quoteCode;
}
