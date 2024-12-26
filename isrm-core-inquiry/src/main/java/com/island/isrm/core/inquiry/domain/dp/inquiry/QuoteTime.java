package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 报价时间
 * <p>
 * 该类用于表示一个报价的时间区间，包括起始时间和结束时间
 * 主要功能是确保报价时间区间的正确性，即起始时间不能晚于结束时间
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class QuoteTime {
    // 报价的起始时间
    private final LocalDateTime startTime;
    // 报价的结束时间
    private final LocalDateTime endTime;

    /**
     * 构造一个报价时间对象
     *
     * @param startTime 报价的起始时间，不能为空
     * @param endTime   报价的结束时间，不能为空
     * @throws IllegalArgumentException 如果起始时间或结束时间为null，或者起始时间晚于结束时间，则抛出该异常
     */
    public QuoteTime(LocalDateTime startTime, LocalDateTime endTime) {
        // 检查起始时间和结束时间是否为空
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("起始时间和结束时间不能为空");
        }
        // 检查起始时间是否晚于结束时间
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("起始时间不能晚于结束时间");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
