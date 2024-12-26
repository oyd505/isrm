package com.island.isrm.core.inquiry.port.repo.dao.projection;

/**
 * 询价请求的基础信息
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public interface InquiryRequestBasic {

    /**
     * 获取询价编码
     *
     * @return 询价编码，唯一标识一个询价请求
     */
    String getInquiryCode();

    /**
     * 获取询价请求的标题
     *
     * @return 标题，描述了询价请求的主题
     */
    String getTitle();

    /**
     * 获取参与情况
     *
     * @return 参与情况，说明了参与询价的供应商或部门
     */
    String getParticipation();

    /**
     * 获取买家名称
     *
     * @return 买家名称，发起询价请求的实体
     */
    String getBuyerName();

    /**
     * 获取询价请求的状态
     *
     * @return 状态，描述了询价请求当前所处的阶段或状况
     */
    String getStatus();

}
