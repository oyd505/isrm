package com.island.isrm.core.supplier.domain.dp;

/**
 * 供应商状态
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public enum SupplierStatus {
    /**
     * 临时
     */
    TEMPORARY,
    /**
     * 潜在
     */
    POTENTIAL {
        @Override
        public boolean isEditable() {
            return true;
        }
    },
    /**
     * 引入中
     */
    INTRODUCING,
    /**
     * 合格
     */
    QUALIFIED,
    /**
     * 已退出
     */
    EXITED;

    public boolean isEditable() {
        return false;
    }
}
