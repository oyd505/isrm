package com.island.isrm.core.inquiry.domain.external;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;

/**
 * 询价编码服务
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public interface InquiryCodeService {

    /**
     * 生成询价编码
     *
     * @return 询价编码
     */
    InquiryCode generateSerialNumber();
}
