package com.island.isrm.core.inquiry.application.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddInquirySupplierCmd extends ApplyInquirySupplierCmd {
    private String audited;
}
