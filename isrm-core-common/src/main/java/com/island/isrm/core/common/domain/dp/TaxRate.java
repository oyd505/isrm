package com.island.isrm.core.common.domain.dp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 税率
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class TaxRate {
    private final BigDecimal value;
}
