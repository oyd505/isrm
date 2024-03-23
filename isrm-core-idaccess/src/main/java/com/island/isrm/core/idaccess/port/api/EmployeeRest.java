package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeCodeAndName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeRest {

    @GetMapping("/list/code/name")
    public List<EmployeeCodeAndName> listCodeAndName() {
        List<EmployeeCodeAndName> data = new ArrayList<>();
        data.add(new EmployeeCodeAndName("E1000", "北堂宇"));
        data.add(new EmployeeCodeAndName("E1001", "西门清"));
        data.add(new EmployeeCodeAndName("E1002", "司马晨"));
        data.add(new EmployeeCodeAndName("E1003", "龙浩然"));
        data.add(new EmployeeCodeAndName("E1004", "凤九天"));
        data.add(new EmployeeCodeAndName("E1005", "慕容云"));
        data.add(new EmployeeCodeAndName("E1006", "欧阳晨"));
        data.add(new EmployeeCodeAndName("E1007", "令狐飞"));
        data.add(new EmployeeCodeAndName("E1008", "东方明"));
        data.add(new EmployeeCodeAndName("E1009", "南宫轩"));
        return data;
    }
}
