package com.island.isrm.core.inquiry.domain.dp.inquiry;

import lombok.Data;
import lombok.NonNull;

/**
 * 询价供应商ID
 * 用于唯一标识一个供应商在询价过程中的身份
 * 
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Data
public class InquirySupplierId {
    /**
     * 询价供应商ID
     * 用于标识特定的供应商，是供应商在询价中的唯一键值
     */
    @NonNull
    private final Long id;
}
