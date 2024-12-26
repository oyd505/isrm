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

/**
 * 员工信息RESTful API接口
 * 提供员工信息的查询、创建、更新和删除等操作
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@RestController
@RequestMapping("/v1/employee")
public class EmployeeRest {
    private final EmployeeAppService employeeAppService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeeRest(EmployeeAppService employeeAppService, EmployeeQueryService employeeQueryService) {
        this.employeeAppService = employeeAppService;
        this.employeeQueryService = employeeQueryService;
    }

    /**
     * 分页查询员工基本信息
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 员工基本信息的分页结果
     */
    @GetMapping("/page/basic")
    public Page<EmployeeBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return this.employeeQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 查询所有员工的编码和名称
     *
     * @return 员工编码和名称的列表
     */
    @GetMapping("/list/code/name")
    public List<EmployeeCodeAndName> listCodeAndName() {
        return employeeQueryService.listCodeAndName();
    }

    /**
     * 根据员工编码查询员工详细信息
     *
     * @param employeeCode 员工编码
     * @return 员工详细信息
     */
    @GetMapping("/{employeeCode}")
    public EmployeeDO find(@PathVariable String employeeCode) {
        return employeeQueryService.find(employeeCode);
    }

    /**
     * 创建新员工
     *
     * @param command 创建员工的命令对象，包含员工相关信息
     * @return 新创建员工的编码
     */
    @PostMapping("/create")
    public String create(@RequestBody CreateEmployeeCmd command) {
        return employeeAppService.create(command).getValue();
    }

    /**
     * 更新员工信息
     *
     * @param command 更新员工的命令对象，包含需要更新的员工信息
     */
    @PostMapping("/update")
    public void update(@RequestBody UpdateEmployeeCmd command) {
        employeeAppService.update(command);
    }

    /**
     * 删除员工
     *
     * @param employeeCode 需要删除的员工的编码
     */
    @PostMapping("/remove/{employeeCode}")
    public void remove(@PathVariable String employeeCode) {
        employeeAppService.remove(new EmployeeCode(employeeCode));
    }
}
