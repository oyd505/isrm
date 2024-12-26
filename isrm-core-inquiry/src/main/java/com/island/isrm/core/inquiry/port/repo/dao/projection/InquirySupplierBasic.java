package com.island.isrm.core.inquiry.port.repo.dao.projection;

/**
 * 询价供应商信息查询接口
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface InquirySupplierBasic {

    /**
     * 获取供应商ID
     *
     * @return 供应商ID
     */
    Long getId();

    /**
     * 获取询价编码
     *
     * @return 询价编码
     */
    String getInquiryCode();

    /**
     * 获取供应商编码
     *
     * @return 供应商编码
     */
    String getCode();

    /**
     * 获取供应商名称
     *
     * @return 供应商名称
     */
    String getName();

    /**
     * 获取联系人姓名
     *
     * @return 联系人姓名
     */
    String getContactName();

    /**
     * 获取联系人电话
     *
     * @return 联系人电话
     */
    String getContactPhone();

    /**
     * 获取审核状态
     *
     * @return 审核状态
     */
    String getAudited();

    /**
     * 获取确认状态
     *
     * @return 确认状态
     */
    String getConfirmed();
}
