package com.island.isrm.core.idaccess.port.message;

import com.island.isrm.core.common.domain.event.EmployeeCreatedEvent;
import com.island.isrm.core.idaccess.application.UserAppService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听员工创建事件并处理用户创建逻辑
 * <p>
 * 本类的作用是监听系统中员工创建事件（EmployeeCreatedEvent），
 * 并在事件触发时调用用户应用服务（UserAppService）中的创建方法，
 * 以确保系统中用户账户的创建与员工创建同步进行
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Component
public class EmployeeUserCreationListener {
    // 用户应用服务，用于执行用户创建操作
    private final UserAppService userAppService;

    /**
     * 构造方法，注入用户应用服务
     *
     * @param userAppService 用户应用服务实例，用于处理用户相关的业务逻辑
     */
    public EmployeeUserCreationListener(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /**
     * 处理员工创建事件的方法
     * <p>
     * 当系统中发生员工创建事件时，本方法会自动被调用
     * 它将事件对象作为参数传递给用户应用服务的创建方法，
     * 以处理相应的用户创建逻辑
     *
     * @param event 员工创建事件，包含新员工的相关信息
     */
    @EventListener
    public void handleEmployeeCreatedEvent(EmployeeCreatedEvent event) {
        userAppService.create(event);
    }
}
