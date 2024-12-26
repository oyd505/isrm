package com.island.isrm.core.idaccess.domain.dp;

/**
 * 用户类型枚举类，用于定义不同类型的用户
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public enum UserType {
    /**
     * 管理员
     */
    ADMIN {
        @Override
        public boolean isAdmin() {
            return true;
        }

        @Override
        public boolean isExternal() {
            return true;
        }
    },
    /**
     * 内部员工
     */
    EMPLOYEE,
    /**
     * 外部供应商
     */
    SUPPLIER {
        @Override
        public boolean isExternal() {
            return true;
        }
    };

    /**
     * 判断用户类型是否为外部用户
     *
     * @return 默认情况下，返回false
     */
    public boolean isExternal() {
        return false;
    }

    /**
     * 判断用户类型是否为管理员
     *
     * @return 默认情况下，返回false
     */
    public boolean isAdmin() {
        return false;
    }
}
