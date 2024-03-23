package com.island.isrm.core.common.domain;

import com.island.isrm.core.common.domain.dp.CreatedBy;
import com.island.isrm.core.common.domain.dp.CreatedDate;
import com.island.isrm.core.common.domain.dp.Version;
import lombok.Data;

@Data
public class BaseEntity {
    private CreatedBy createdBy;
    private CreatedDate createdDate;
    private Version version;
}
