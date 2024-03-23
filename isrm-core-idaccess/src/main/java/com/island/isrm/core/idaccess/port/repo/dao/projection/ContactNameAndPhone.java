package com.island.isrm.core.idaccess.port.repo.dao.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactNameAndPhone {
    private String supplierCode;
    private String name;
    private String phone;
}
