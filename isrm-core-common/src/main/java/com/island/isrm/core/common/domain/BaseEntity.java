package com.island.isrm.core.common.domain;

import com.island.isrm.core.common.domain.dp.CreatedBy;
import com.island.isrm.core.common.domain.dp.CreatedDate;
import com.island.isrm.core.common.domain.dp.Version;
import lombok.Data;

/**
 * 基础领域实体类，提供所有实体类共有的基本属性
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class BaseEntity {
    /**
     * 创建者信息，用于记录实体的创建人
     */
    private CreatedBy createdBy;

    /**
     * 创建日期信息，用于记录实体的创建时间
     */
    private CreatedDate createdDate;

    /**
     * 版本信息，用于乐观锁控制，以防止并发更新时的数据不一致
     */
    private Version version;
}
