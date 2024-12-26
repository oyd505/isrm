package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 报价进度
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class QuoteProgress {
    // 报价次数
    private final Integer quoteCount;
    // 报价编码
    private final String quoteCode;

    /**
     * 构造函数
     *
     * @param quoteCount 报价次数，如果为null或负数，则默认为0
     * @param quoteCode  报价编码
     */
    public QuoteProgress(Integer quoteCount, String quoteCode) {
        if (quoteCount == null || quoteCount < 0) {
            this.quoteCount = 0;
        } else {
            this.quoteCount = quoteCount;
        }
        this.quoteCode = quoteCode;
    }

    /**
     * 创建下一个报价进度
     *
     * @param quoteCode 新的报价编码
     * @return 新的报价进度
     * @throws IllegalArgumentException 如果报价编码为空，则抛出异常
     */
    public QuoteProgress createNextProgress(String quoteCode) {
        if (!StringUtils.hasText(quoteCode)) {
            throw new IllegalArgumentException("报价编码不能为空");
        }
        if (!StringUtils.hasText(this.quoteCode) || this.quoteCount == null) {
            return new QuoteProgress(1, quoteCode);
        }
        if (!this.quoteCode.equals(quoteCode)) {
            return new QuoteProgress(this.quoteCount + 1, quoteCode);
        }
        return new QuoteProgress(this.quoteCount, this.quoteCode);
    }
}
