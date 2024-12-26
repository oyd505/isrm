package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;

import java.time.LocalDate;

/**
 * 交货日期
 *
 * 该类用于表示一个交货的日期，使用Java 8的LocalDate类来存储日期信息。
 * 通过使用lombok的@Data注解，自动为该类生成getter和setter方法，
 * 以及构造函数和toString方法等常用方法。
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class DeliveryDate {
    /**
     * 交货的具体日期
     */
    private final LocalDate date;
}
