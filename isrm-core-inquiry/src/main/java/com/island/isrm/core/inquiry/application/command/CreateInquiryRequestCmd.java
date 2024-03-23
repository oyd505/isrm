package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateInquiryRequestCmd {
    @NotBlank
    private String title;
    private String reason;
    @NotBlank
    private String participation;
    @NotBlank
    private String buyerCode;
    private String buyerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String paymentTerms;
    private String currency;
    private String purOrgCode;
    private String purOrgName;
}
