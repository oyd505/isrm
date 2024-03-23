package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.time.LocalDate;

/**
 * 交货日期
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class DeliveryDate {
    private final LocalDate date;
}
