package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInquiryProductCmd extends AddInquiryProductCmd {
    @NotNull
    @Positive
    private Long id;
}
