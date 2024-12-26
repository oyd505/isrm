package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;
import lombok.NonNull;

/**
 * 询问商品ID类
 * 用于唯一标识询问表中的商品
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class InquiryProductId {
    /**
     * 商品ID
     * 使用Long类型来表示商品ID，确保ID的唯一性和扩展性
     */
    @NonNull
    private final Long id;
}
