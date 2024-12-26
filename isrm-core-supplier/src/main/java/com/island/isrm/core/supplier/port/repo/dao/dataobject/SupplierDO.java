package com.island.isrm.core.supplier.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 供应商数据对象类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "supplier")
@EntityListeners(AuditingEntityListener.class)
public class SupplierDO extends BaseDataObject {
    /**
     * 供应商编码
     */
    @Id
    @Column(nullable = false, length = 32)
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商状态
     */
    @Column(length = 32)
    private String supplierStatus;
}
