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

/**
 * 用户管理REST接口
 * 提供用户相关操作的HTTP访问接口，如分页查询、创建、更新、删除用户等
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
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

    /**
     * 分页查询用户基本信息
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 用户基本信息的分页结果
     */
    @GetMapping("/page/basic")
    public Page<UserBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return userQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 根据用户名查找用户详细信息
     *
     * @param userName 用户名
     * @return 用户详细信息
     */
    @GetMapping("/{userName}")
    public UserDO find(@PathVariable String userName) {
        return userQueryService.find(userName);
    }

    /**
     * 创建新用户
     *
     * @param command 创建用户的命令对象，包含用户相关信息
     * @return 新创建用户的标识
     */
    @PostMapping("/create")
    public String create(@RequestBody CreateUserCmd command) {
        return userAppService.create(command).getValue();
    }

    /**
     * 更新用户信息
     *
     * @param command 更新用户的命令对象，包含需要更新的用户信息
     */
    @PostMapping("/update")
    public void update(@RequestBody UpdateUserCmd command) {
        userAppService.update(command);
    }

    /**
     * 更新用户密码
     *
     * @param userName 用户名
     * @param command  更新密码的命令对象，包含原密码和新密码
     */
    @PostMapping("/update/{userName}/password")
    public void updatePassword(@PathVariable String userName, @RequestBody UpdatePasswordCmd command) {
        userAppService.updatePassword(new UserName(userName), command);
    }

    /**
     * 更新用户角色
     *
     * @param userName 用户名
     * @param roles    用户的新角色列表
     */
    @PostMapping("/update/{userName}/roles")
    public void updateRoles(@PathVariable String userName, @RequestBody List<String> roles) {
        userAppService.updateRoles(new UserName(userName), roles);
    }

    /**
     * 禁用用户
     *
     * @param userName 用户名
     */
    @PostMapping("/disable/{userName}")
    public void disable(@PathVariable String userName) {
        userAppService.disable(new UserName(userName));
    }

    /**
     * 启用用户
     *
     * @param userName 用户名
     */
    @PostMapping("/enable/{userName}")
    public void enable(@PathVariable String userName) {
        userAppService.enable(new UserName(userName));
    }

    /**
     * 删除用户
     *
     * @param userName 用户名
     */
    @PostMapping("/remove/{userName}")
    public void remove(@PathVariable String userName) {
        userAppService.remove(new UserName(userName));
    }
}
