package com.island.isrm.core.common.port.repo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * 基础数据对象类，提供所有实体类共有的基本属性
 * 这些属性包括创建时间、修改时间、创建者和修改者信息，以及版本号
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@MappedSuperclass
@Data
public class BaseDataObject {
    /**
     * 创建日期，实体类创建时自动设置
     */
    @CreatedDate
    private LocalDateTime createdDate;
    /**
     * 最后修改日期，实体类每次更新时自动设置
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    /**
     * 创建者，记录创建实体类的用户信息
     */
    @CreatedBy
    @Column(length = 64)
    private String createdBy;
    /**
     * 最后修改者，记录最后修改实体类的用户信息
     */
    @LastModifiedBy
    private String lastModifiedBy;

    /**
     * 版本号，用于乐观锁机制，每次更新实体类时递增
     */
    @Version
    private Integer version;
}
