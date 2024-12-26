package com.island.isrm.core.inquiry.domain.dp.inquiry;

/**
 * 参与方式
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public enum Participation {
    // 表示被邀请参与
    INVITED, 
    // 表示开放参与
    OPEN {
        @Override
        public boolean isOpen() {
            return true;
        }
    };

    /**
     * 是否开放参与
     *
     * @return true：开放参与；false：不开放参与
     */
    public boolean isOpen() {
        return false;
    }
}
