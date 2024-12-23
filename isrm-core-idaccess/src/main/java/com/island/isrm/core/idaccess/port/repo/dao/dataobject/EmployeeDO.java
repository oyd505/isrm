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
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class EmployeeDO extends BaseDataObject {
    @Id
    @Column(nullable = false, length = 32)
    private String employeeCode; // 员工编码
    private String name; // 员工姓名
    @Column(length = 32)
    private String departmentCode;
    private String departmentName;
    private String jobTitle;
    @Column(length = 32)
    private String phone;
    @Column(length = 64)
    private String email;
}
