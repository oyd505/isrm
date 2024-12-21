package com.island.isrm.core.idaccess.port.repo.dao.projection;

public interface UserBasic {
    String getUserName();

    String getNickname();

    String getUserType();

    String getSupplierCode();

    String getSupplierName();

    String getEmployeeCode();

    String getEmployeeName();

    String getRoles();

    boolean getDisabled();

    boolean getAccountLocked();
}
