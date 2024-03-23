package com.island.isrm.core.common.domain.dp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 价格
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Price {
    private final BigDecimal value;
    private final BigDecimal taxRate;
}
