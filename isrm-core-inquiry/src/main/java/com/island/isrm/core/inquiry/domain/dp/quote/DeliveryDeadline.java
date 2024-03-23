package com.island.isrm.core.inquiry.domain.dp.quote;

import lombok.Data;

import java.time.LocalDate;

/**
 * 交付期限
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class DeliveryDeadline {
    private final LocalDate date;
}
