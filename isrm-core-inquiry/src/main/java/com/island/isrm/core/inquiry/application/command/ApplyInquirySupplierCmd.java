package com.island.isrm.core.inquiry.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 申请参与询价供应商命令类
 * 用于处理供应商申请查询的相关信息和数据
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Data
public class ApplyInquirySupplierCmd {
    /**
     * 供应商编码
     * 使用@NotBlank注解确保编码不为空，这是查询供应商所必需的信息
     */
    @NotBlank
    private String code;

    /**
     * 供应商名称
     * 使用@NotBlank注解确保名称不为空，这是查询供应商所必需的信息
     */
    @NotBlank
    private String name;

    /**
     * 联系人姓名
     * 使用@NotBlank注解确保联系人姓名不为空，这是查询供应商所必需的信息
     */
    @NotBlank
    private String contactName;

    /**
     * 联系人电话
     * 使用@NotBlank注解确保联系人电话不为空，这是查询供应商所必需的信息
     */
    @NotBlank
    private String contactPhone;
}
