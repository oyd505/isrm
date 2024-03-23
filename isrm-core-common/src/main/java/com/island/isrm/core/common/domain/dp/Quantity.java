package com.island.isrm.core.common.domain.dp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 数量
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class Quantity {
    private final BigDecimal value;
    private final String units;
}
