package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateQuoteRequestCmd {
    @NotBlank
    private String quoteCode;
    private String currency;
}
