package com.island.isrm.core.inquiry.port.repo.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InquirySupplierProducts {
    private String inquiryCode;
    private String status;
    private Supplier supplier;
    private List<Product> products;

    @Data
    public static class Supplier {
        private String code;
        private String name;
        private String confirmed;
        private String audited;
    }

    @Data
    public static class Product {
        private String code;
        private String name;
        private BigDecimal quantity;
        private Integer lineNumber;
    }
}
