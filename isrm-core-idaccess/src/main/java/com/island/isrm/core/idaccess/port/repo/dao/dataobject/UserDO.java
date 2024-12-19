package com.island.isrm.core.idaccess.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class UserDO extends BaseDataObject {
    @Id
    @Column(nullable = false, length = 32)
    private String userCode;
    private String name;
    private String password;
    @Column(length = 32)
    private String userType;
    private String roles;
    @Column(length = 32)
    private String supplierCode;
    private String supplierName;
    private String employeeCode;
    private String employeeName;
    private boolean disabled;
    private boolean accountLocked;
}
