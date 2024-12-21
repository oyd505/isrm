package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.application.UserAppService;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdatePasswordCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.port.repo.dao.dataobject.UserDO;
import com.island.isrm.core.idaccess.port.repo.dao.projection.UserBasic;
import com.island.isrm.core.idaccess.port.repo.service.UserQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserRest {
    private final UserQueryService userQueryService;
    private final UserAppService userAppService;

    public UserRest(UserQueryService userQueryService, UserAppService userAppService) {
        this.userQueryService = userQueryService;
        this.userAppService = userAppService;
    }

    @GetMapping("/page/basic")
    public Page<UserBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return userQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{userName}")
    public UserDO find(@PathVariable String userName) {
        return userQueryService.find(userName);
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateUserCmd command) {
        return userAppService.create(command).getValue();
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateUserCmd command) {
        userAppService.update(command);
    }

    @PostMapping("/update/{userName}/password")
    public void updatePassword(@PathVariable String userName, @RequestBody UpdatePasswordCmd command) {
        userAppService.updatePassword(new UserName(userName), command);
    }

    @PostMapping("/update/{userName}/roles")
    public void updateRoles(@PathVariable String userName, @RequestBody List<String> roles) {
        userAppService.updateRoles(new UserName(userName), roles);
    }

    @PostMapping("/disable/{userName}")
    public void disable(@PathVariable String userName) {
        userAppService.disable(new UserName(userName));
    }

    @PostMapping("/enable/{userName}")
    public void enable(@PathVariable String userName) {
        userAppService.enable(new UserName(userName));
    }

    @PostMapping("/remove/{userName}")
    public void remove(@PathVariable String userName) {
        userAppService.remove(new UserName(userName));
    }
}
