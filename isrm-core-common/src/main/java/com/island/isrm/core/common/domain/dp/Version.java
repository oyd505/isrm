package com.island.isrm.core.common.domain.dp;

import lombok.Data;
import lombok.NonNull;

/**
 * 版本号
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Version {
    @NonNull
    private final Integer value;
}
