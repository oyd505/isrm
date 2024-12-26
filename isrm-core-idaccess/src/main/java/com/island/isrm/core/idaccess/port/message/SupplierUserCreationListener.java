package com.island.isrm.core.idaccess.port.message;

import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.idaccess.application.UserAppService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听供应商创建事件的监听器.
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Component
public class SupplierUserCreationListener {
    /**
     * 用户应用服务，用于创建用户.
     */
    private final UserAppService userAppService;

    /**
     * 构造函数，注入用户应用服务.
     *
     * @param userAppService 用户应用服务.
     */
    public SupplierUserCreationListener(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /**
     * 处理供应商创建事件，当供应商创建事件发生时，调用用户应用服务创建用户.
     *
     * @param event 供应商创建事件，包含创建供应商的相关信息.
     */
    @EventListener
    public void handleSupplierCreatedEvent(SupplierCreatedEvent event) {
        this.userAppService.create(event);
    }

    /**
     * 处理供应商联系人创建事件，当供应商联系人创建事件发生时，调用用户应用服务创建用户.
     *
     * @param event 供应商联系人创建事件，包含创建供应商联系人的相关信息.
     */
    @EventListener
    public void handleSupplierContactCreatedEvent(SupplierContactCreatedEvent event) {
        this.userAppService.create(event);
    }
}
