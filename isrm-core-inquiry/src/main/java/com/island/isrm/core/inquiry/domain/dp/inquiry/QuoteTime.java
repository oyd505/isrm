package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 报价时间
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class QuoteTime {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public QuoteTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("起始时间和结束时间不能为空");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("起始时间不能晚于结束时间");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
