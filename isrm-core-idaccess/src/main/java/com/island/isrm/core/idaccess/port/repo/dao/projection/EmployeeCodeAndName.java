package com.island.isrm.core.idaccess.port.repo.dao.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeCodeAndName {
    private String code;
    private String name;
}
