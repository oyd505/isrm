package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddInquiryProductCmd {
    @NotBlank
    private String inquiryCode;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotNull
    @DecimalMin("0")
    private BigDecimal quantity;
    @NotBlank
    private String units;
    private String factoryCode;
    private String factoryName;
    @NotNull
    @Future
    private LocalDate deliveryDate;
}
