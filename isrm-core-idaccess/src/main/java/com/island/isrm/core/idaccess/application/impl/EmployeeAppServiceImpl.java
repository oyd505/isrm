package com.island.isrm.core.idaccess.application.impl;

import com.island.isrm.core.common.domain.event.EmployeeCreatedEvent;
import com.island.isrm.core.common.domain.external.DomainEventPublisher;
import com.island.isrm.core.idaccess.application.EmployeeAppService;
import com.island.isrm.core.idaccess.application.EmployeeAssembler;
import com.island.isrm.core.idaccess.application.command.CreateEmployeeCmd;
import com.island.isrm.core.idaccess.application.command.UpdateEmployeeCmd;
import com.island.isrm.core.idaccess.domain.dp.EmployeeCode;
import com.island.isrm.core.idaccess.domain.entity.Employee;
import com.island.isrm.core.idaccess.domain.external.EmployeeCodeService;
import com.island.isrm.core.idaccess.domain.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EmployeeAppServiceImpl类实现了EmployeeAppService接口，提供员工应用层的服务实现。
 * 它负责处理员工的创建、更新和删除等操作，并协调与其他服务的交互，如事件发布。
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@Service
public class EmployeeAppServiceImpl implements EmployeeAppService {
    // EmployeeAssembler实例，用于员工数据的转换和映射
    private final EmployeeAssembler employeeAssembler = EmployeeAssembler.instance;
    // EmployeeRepository实例，用于访问员工数据
    private final EmployeeRepository employeeRepository;
    // EmployeeCodeService实例，用于生成员工编码
    private final EmployeeCodeService employeeCodeService;
    // DomainEventPublisher实例，用于发布领域事件
    private final DomainEventPublisher publisher;

    /**
     * 构造函数，通过@Autowired注解进行依赖注入
     *
     * @param employeeRepository  员工仓储接口
     * @param employeeCodeService 员工编码服务接口
     * @param publisher           领域事件发布器
     */
    @Autowired
    public EmployeeAppServiceImpl(EmployeeRepository employeeRepository, EmployeeCodeService employeeCodeService, DomainEventPublisher publisher) {
        this.employeeRepository = employeeRepository;
        this.employeeCodeService = employeeCodeService;
        this.publisher = publisher;
    }

    /**
     * 创建员工的方法
     * 该方法首先生成一个员工编码，然后将创建员工的命令转换为员工实体并保存到仓储中，
     * 最后发布员工创建事件。
     *
     * @param command 创建员工的命令，包含员工的相关信息
     * @return 返回生成的员工编码
     */
    @Override
    public EmployeeCode create(CreateEmployeeCmd command) {
        EmployeeCode employeeCode = employeeCodeService.generateSerialNumber();
        Employee employee = employeeAssembler.toAddEntity(command, employeeCode);
        employeeRepository.add(employee);
        publisher.publishEvent(new EmployeeCreatedEvent(this, employeeCode.getValue(), employee.getName().getValue()));
        return employeeCode;
    }

    /**
     * 更新员工信息的方法
     * 该方法将更新员工的命令转换为员工实体，根据员工编码查找需要更新的员工，
     * 并将新的信息应用到该员工上，最后保存更新。
     *
     * @param command 更新员工的命令，包含需要更新的员工信息
     */
    @Override
    public void update(UpdateEmployeeCmd command) {
        Employee input = employeeAssembler.toUpdateEntity(command);
        Employee employee = employeeRepository.find(input.getEmployeeCode());
        employee.update(input);
        employeeRepository.update(employee);
    }

    /**
     * 删除员工的方法
     * 该方法根据员工编码查找员工，并将其从系统中移除。
     *
     * @param employeeCode 要删除的员工的编码
     */
    @Override
    public void remove(EmployeeCode employeeCode) {
        employeeRepository.find(employeeCode);
        employeeRepository.remove(employeeCode);
    }
}
