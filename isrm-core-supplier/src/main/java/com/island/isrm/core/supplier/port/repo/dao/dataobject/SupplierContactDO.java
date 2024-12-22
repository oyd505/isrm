package com.island.isrm.core.supplier.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 供应商联系人数据对象
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "supplier_contact")
@EntityListeners(AuditingEntityListener.class)
public class SupplierContactDO extends BaseDataObject {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 供应商编码
     */
    @Column(nullable = false, length = 32)
    private String supplierCode;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;
}
