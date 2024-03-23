package com.island.isrm.core.common.domain.dp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 金额
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Amount {
    private final BigDecimal value;
}
