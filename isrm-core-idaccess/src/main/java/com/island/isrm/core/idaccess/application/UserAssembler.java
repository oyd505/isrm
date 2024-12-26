package com.island.isrm.core.idaccess.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.idaccess.application.command.CreateUserCmd;
import com.island.isrm.core.idaccess.application.command.UpdateUserCmd;
import com.island.isrm.core.idaccess.domain.dp.UserEmployee;
import com.island.isrm.core.idaccess.domain.dp.UserName;
import com.island.isrm.core.idaccess.domain.dp.UserSupplier;
import com.island.isrm.core.idaccess.domain.dp.UserType;
import com.island.isrm.core.idaccess.domain.entity.User;

/**
 * 用户组装器类，用于将命令对象转换为用户实体对象
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
public class UserAssembler {
    // 单例实例，提供全局唯一的用户组装器实例
    public final static UserAssembler instance = new UserAssembler();

    /**
     * 将创建用户命令和用户名转换为用户实体对象
     *
     * @param command  创建用户命令对象，包含用户相关信息
     * @param userName 用户名对象，包含用户名称信息
     * @return 返回组装好的用户实体对象
     */
    public User toAddEntity(CreateUserCmd command, UserName userName) {
        User user = new User(userName);
        this.assemble(user, command);
        return user;
    }

    /**
     * 为供应商创建用户实体对象
     *
     * @param supplierCode 供应商编码
     * @param supplierName 供应商名称
     * @return 返回组装好的用户实体对象
     */
    public User toAddEntityForSupplier(String supplierCode, String supplierName) {
        return this.toAddEntityForSupplier(supplierCode, supplierName, supplierCode, supplierName);
    }

    /**
     * 为供应商创建用户实体对象
     *
     * @param userName     用户名
     * @param nickname     昵称
     * @param supplierCode 供应商编码
     * @param supplierName 供应商名称
     * @return 返回组装好的用户实体对象
     */
    public User toAddEntityForSupplier(String userName, String nickname, String supplierCode, String supplierName) {
        User user = new User(new UserName(userName));
        user.setNickname(new Name(nickname));
        user.setUserType(UserType.SUPPLIER);
        user.setUserSupplier(new UserSupplier(supplierCode, supplierName));
        return user;
    }

    /**
     * 为员工创建用户实体对象
     *
     * @param employeeCode 员工编码
     * @param employeeName 员工名称
     * @return 返回组装好的用户实体对象
     */
    public User toAddEntityForEmployee(String employeeCode, String employeeName) {
        User user = new User(new UserName(employeeCode));
        user.setNickname(new Name(employeeName));
        user.setUserType(UserType.EMPLOYEE);
        user.setUserEmployee(new UserEmployee(employeeCode, employeeName));
        return user;
    }

    /**
     * 组装用户实体对象
     *
     * @param user    用户实体对象
     * @param command 创建用户命令对象，包含用户相关信息
     */
    private void assemble(User user, CreateUserCmd command) {
        user.setNickname(new Name(command.getNickname()));
        user.setUserType(UserType.valueOf(command.getUserType()));
        user.setUserEmployee(new UserEmployee(command.getEmployeeCode(), command.getEmployeeName()));
        user.setUserSupplier(new UserSupplier(command.getSupplierCode(), command.getSupplierName()));
    }

    /**
     * 将更新用户命令转换为用户实体对象
     *
     * @param command 更新用户命令对象，包含用户相关信息
     * @return 返回组装好的用户实体对象
     */
    public User toUpdateEntity(UpdateUserCmd command) {
        User update = new User(new UserName(command.getUserName()));
        this.assemble(update, command);
        return update;
    }
}
