package com.island.isrm.core.idaccess.domain.dp;

public enum UserType {
    ADMIN {
        @Override
        public boolean isAdmin() {
            return true;
        }
    }, EMPLOYEE,
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
