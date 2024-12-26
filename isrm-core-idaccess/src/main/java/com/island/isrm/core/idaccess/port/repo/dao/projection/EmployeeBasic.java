/**
 * 员工基本信息接口
 * 该接口定义了员工基本信息的读取方法，用于获取员工的关键信息
 * 包括员工编码、姓名、部门编码、部门名称、职务、电话和邮箱
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
package com.island.isrm.core.idaccess.port.repo.dao.projection;

public interface EmployeeBasic {

    /**
     * 获取员工编码
     * 员工编码是员工在系统中的唯一标识
     *
     * @return 员工编码
     */
    String getEmployeeCode();

    /**
     * 获取员工姓名
     * 员工姓名是员工在公司中的称呼
     *
     * @return 员工姓名
     */
    String getName();

    /**
     * 获取部门编码
     * 部门编码是员工所属部门在系统中的唯一标识
     *
     * @return 部门编码
     */
    String getDepartmentCode();

    /**
     * 获取部门名称
     * 部门名称是员工所属部门在公司中的称呼
     *
     * @return 部门名称
     */
    String getDepartmentName();

    /**
     * 获取员工职务
     * 职务反映了员工在公司中的角色或职位
     *
     * @return 员工职务
     */
    String getJobTitle();

    /**
     * 获取员工电话号码
     * 电话号码用于同事、客户或供应商联系员工
     *
     * @return 员工电话号码
     */
    String getPhone();

    /**
     * 获取员工电子邮件地址
     * 电子邮件地址是员工在公司中的官方联系方式之一
     *
     * @return 员工电子邮件地址
     */
    String getEmail();
}
