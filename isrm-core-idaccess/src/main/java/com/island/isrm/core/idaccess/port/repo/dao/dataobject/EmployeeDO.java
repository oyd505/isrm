package com.island.isrm.core.idaccess.port.repo.dao.dataobject;

import com.island.isrm.core.common.port.repo.BaseDataObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * EmployeeDO类代表员工数据对象，继承自BaseDataObject，用于映射员工表中的记录
 * 该类使用了Lombok注解来简化getter、setter以及equals和hashcode的实现，同时使用了JPA注解来定义数据库映射
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class EmployeeDO extends BaseDataObject {
    /**
     * 员工编码，作为主键，长度为32，不为空
     */
    @Id
    @Column(nullable = false, length = 32)
    private String employeeCode;
    
    /**
     * 员工姓名
     */
    private String name;
    
    /**
     * 部门编码，长度为32
     */
    @Column(length = 32)
    private String departmentCode;
    
    /**
     * 部门名称
     */
    private String departmentName;
    
    /**
     * 职位名称
     */
    private String jobTitle;
    
    /**
     * 电话号码，长度为32
     */
    @Column(length = 32)
    private String phone;
    
    /**
     * 电子邮件地址，长度为64
     */
    @Column(length = 64)
    private String email;
}
