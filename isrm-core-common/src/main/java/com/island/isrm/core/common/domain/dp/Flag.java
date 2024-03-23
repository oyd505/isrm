package com.island.isrm.core.common.domain.dp;

/**
 * 标识
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public enum Flag {
    N, Y {
        @Override
        public boolean isOk() {
            return true;
        }
    };

    public boolean isOk() {
        return false;
    }
}
