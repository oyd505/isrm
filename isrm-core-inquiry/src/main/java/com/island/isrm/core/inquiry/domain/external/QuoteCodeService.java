package com.island.isrm.core.inquiry.domain.external;

import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;

/**
 * 报价编码服务
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public interface QuoteCodeService {

    /**
     * 生成报价编码
     *
     * @return 报价编码
     */
    QuoteCode generateSerialNumber();
}
