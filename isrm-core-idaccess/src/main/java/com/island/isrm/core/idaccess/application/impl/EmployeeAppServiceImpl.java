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

@Service
public class EmployeeAppServiceImpl implements EmployeeAppService {
    private final EmployeeAssembler employeeAssembler = EmployeeAssembler.instance;
    private final EmployeeRepository employeeRepository;
    private final EmployeeCodeService employeeCodeService;
    private final DomainEventPublisher publisher;

    @Autowired
    public EmployeeAppServiceImpl(EmployeeRepository employeeRepository, EmployeeCodeService employeeCodeService, DomainEventPublisher publisher) {
        this.employeeRepository = employeeRepository;
        this.employeeCodeService = employeeCodeService;
        this.publisher = publisher;
    }

    @Override
    public EmployeeCode create(CreateEmployeeCmd command) {
        EmployeeCode employeeCode = employeeCodeService.generateSerialNumber();
        Employee employee = employeeAssembler.toAddEntity(command, employeeCode);
        employeeRepository.add(employee);
        publisher.publishEvent(new EmployeeCreatedEvent(this, employeeCode.getValue(), employee.getName().getValue()));
        return employeeCode;
    }

    @Override
    public void update(UpdateEmployeeCmd command) {
        Employee input = employeeAssembler.toUpdateEntity(command);
        Employee employee = employeeRepository.find(input.getEmployeeCode());
        employee.update(input);
        employeeRepository.update(employee);
    }

    @Override
    public void remove(EmployeeCode employeeCode) {
        employeeRepository.find(employeeCode);
        employeeRepository.remove(employeeCode);
    }
}
