package com.island.isrm.core.supplier.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "supplier")
@EntityListeners(AuditingEntityListener.class)
public class SupplierDO extends BaseDataObject {
    @Id
    @Column(nullable = false, length = 32)
    private String supplierCode;
    private String name;
    @Column(length = 32)
    private String supplierStatus;
}
