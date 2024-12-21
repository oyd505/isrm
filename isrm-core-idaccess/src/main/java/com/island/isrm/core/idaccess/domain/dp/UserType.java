package com.island.isrm.core.idaccess.domain.dp;

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

    public boolean isExternal() {
        return false;
    }

    public boolean isAdmin() {
        return false;
    }
}
