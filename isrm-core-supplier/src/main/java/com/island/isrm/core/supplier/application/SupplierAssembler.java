package com.island.isrm.core.supplier.application;

import com.island.isrm.core.common.domain.dp.Name;
import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.Contact;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.dp.SupplierStatus;
import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.domain.entity.SupplierContact;

/**
 * 供应商组装器类，用于将命令对象转换为供应商实体对象
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
public class SupplierAssembler {
    // 单例模式，静态实例
    public final static SupplierAssembler instance = new SupplierAssembler();

    /**
     * 将创建供应商命令转换为供应商实体对象
     *
     * @param command        创建供应商命令对象
     * @param supplierCode   供应商编码
     * @param supplierStatus 供应商状态
     * @return 转换后的供应商实体对象
     */
    public Supplier toAddEntity(CreateSupplierCmd command, SupplierCode supplierCode, SupplierStatus supplierStatus) {
        Supplier supplier = new Supplier(supplierCode, supplierStatus);
        this.assemble(command, supplier);
        return supplier;
    }

    /**
     * 将创建供应商命令对象的属性复制到供应商实体对象中
     *
     * @param source 创建供应商命令对象
     * @param target 供应商实体对象
     */
    private void assemble(CreateSupplierCmd source, Supplier target) {
        target.setName(new Name(source.getName()));
    }

    /**
     * 将更新供应商命令转换为供应商实体对象
     *
     * @param command        更新供应商命令对象
     * @param supplierStatus 供应商状态
     * @return 转换后的供应商实体对象
     */
    public Supplier toUpdateEntity(UpdateSupplierCmd command, SupplierStatus supplierStatus) {
        Supplier update = new Supplier(new SupplierCode(command.getSupplierCode()), supplierStatus);
        this.assemble(command, update);
        return update;
    }

    /**
     * 将添加供应商联系人命令转换为供应商联系人实体对象
     *
     * @param supplierCode 供应商编码
     * @param command      添加供应商联系人命令对象
     * @return 转换后的供应商联系人实体对象
     */
    public SupplierContact toAddEntity(SupplierCode supplierCode, AddSupplierContactCmd command) {
        SupplierContact supplierContact = new SupplierContact(null, supplierCode);
        this.assemble(command, supplierContact);
        return supplierContact;
    }

    /**
     * 将添加供应商联系人命令对象的属性复制到供应商联系人实体对象中
     *
     * @param source 添加供应商联系人命令对象
     * @param target 供应商联系人实体对象
     */
    private void assemble(AddSupplierContactCmd source, SupplierContact target) {
        target.setContact(new Contact(source.getName(), source.getPhone()));
    }

    /**
     * 将更新供应商联系人命令转换为供应商联系人实体对象
     *
     * @param supplierCode 供应商编码
     * @param command      更新供应商联系人命令对象
     * @return 转换后的供应商联系人实体对象
     */
    public SupplierContact toUpdateEntity(SupplierCode supplierCode, UpdateSupplierContactCmd command) {
        SupplierContact update = new SupplierContact(new SupplierContactId(command.getId()), supplierCode);
        this.assemble(command, update);
        return update;
    }
}
