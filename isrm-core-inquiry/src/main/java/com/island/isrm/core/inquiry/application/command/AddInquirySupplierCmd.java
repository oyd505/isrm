package com.island.isrm.core.inquiry.application.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加询价供应商命令类
 * 继承自ApplyInquirySupplierCmd，用于发起新增供应商的申请
 * 新增属性audited用于标识供应商是否已审核
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddInquirySupplierCmd extends ApplyInquirySupplierCmd {
    /**
     * 审核状态标识
     * "Y"表示已审核，"N"表示未审核
     */
    private String audited;
}
