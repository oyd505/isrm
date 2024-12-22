package com.island.isrm.core.idaccess.port.message;

import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.idaccess.application.UserAppService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SupplierUserCreationListener {
    private final UserAppService userAppService;

    public SupplierUserCreationListener(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @EventListener
    public void handleSupplierCreatedEvent(SupplierCreatedEvent event) {
        // 根据供应商编码创建用户.
        this.userAppService.create(event);
    }

    @EventListener
    public void handleSupplierContactCreatedEvent(SupplierContactCreatedEvent event) {
        // 根据供应商编码创建用户.
        this.userAppService.create(event);
    }
}
