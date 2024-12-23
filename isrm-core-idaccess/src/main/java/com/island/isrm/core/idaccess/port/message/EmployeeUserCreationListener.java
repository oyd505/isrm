package com.island.isrm.core.idaccess.port.message;

import com.island.isrm.core.common.domain.event.EmployeeCreatedEvent;
import com.island.isrm.core.idaccess.application.UserAppService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUserCreationListener {
    private final UserAppService userAppService;

    public EmployeeUserCreationListener(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @EventListener
    public void handleEmployeeCreatedEvent(EmployeeCreatedEvent event) {
        userAppService.create(event);
    }
}
