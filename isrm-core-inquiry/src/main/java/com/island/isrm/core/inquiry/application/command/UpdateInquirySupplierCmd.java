package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquirySupplierCmd extends ApplyInquirySupplierCmd {
    @NotNull
    @Positive
    private Long id;
    private String audited;
}
