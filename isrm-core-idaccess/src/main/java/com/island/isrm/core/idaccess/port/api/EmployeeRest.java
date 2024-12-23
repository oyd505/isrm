package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.application.EmployeeAppService;
import com.island.isrm.core.idaccess.application.command.CreateEmployeeCmd;
import com.island.isrm.core.idaccess.application.command.UpdateEmployeeCmd;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.EmployeeDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeBasic;
import com.island.isrm.core.idaccess.port.repo.dao.projection.EmployeeCodeAndName;
import com.island.isrm.core.idaccess.port.repo.service.EmployeeQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeRest {
    private final EmployeeAppService employeeAppService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeeRest(EmployeeAppService employeeAppService, EmployeeQueryService employeeQueryService) {
        this.employeeAppService = employeeAppService;
        this.employeeQueryService = employeeQueryService;
    }

    @GetMapping("/page/basic")
    public Page<EmployeeBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return this.employeeQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/list/code/name")
    public List<EmployeeCodeAndName> listCodeAndName() {
        return employeeQueryService.listCodeAndName();
    }

    @GetMapping("/{employeeCode}")
    public EmployeeDO find(@PathVariable String employeeCode) {
        return employeeQueryService.find(employeeCode);
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateEmployeeCmd command) {
        return employeeAppService.create(command).getValue();
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateEmployeeCmd command) {
        employeeAppService.update(command);
    }

    @PostMapping("/remove/{employeeCode}")
    public void remove(@PathVariable String employeeCode) {
        employeeAppService.remove(new EmployeeCode(employeeCode));
    }
}
