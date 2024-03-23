package com.island.isrm.core.common.domain.dp;

import lombok.Data;
import lombok.NonNull;

@Data
public class Version {
    @NonNull
    private final Integer value;
}
