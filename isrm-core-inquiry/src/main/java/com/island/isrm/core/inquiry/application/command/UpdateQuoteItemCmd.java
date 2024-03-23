package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateQuoteItemCmd {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String quoteCode;
    @NotNull
    @Min(value = 1)
    private BigDecimal quoteQuantity;
    @NotBlank
    private String units;
    @NotBlank
    private LocalDate deliveryDeadline;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @Positive
    private BigDecimal taxRate;
}
