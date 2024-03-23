package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquiryRequestCmd extends CreateInquiryRequestCmd {
    @NotBlank
    private String inquiryCode;
}
