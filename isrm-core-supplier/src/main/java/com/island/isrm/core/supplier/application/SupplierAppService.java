package com.island.isrm.core.supplier.application;

import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 定义了供应商应用服务的接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Validated
public interface SupplierAppService {

    /**
     * 创建供应商
     *
     * @param command 包含创建供应商所需信息的命令对象，包括供应商的基本信息和可能的联系人信息等
     * @return 返回生成的供应商编码，作为供应商的唯一标识
     */
    SupplierCode create(@Valid CreateSupplierCmd command);

    /**
     * 更新供应商信息
     *
     * @param command 包含需要更新的供应商信息的命令对象，包括供应商编码和需要修改的信息
     */
    void update(@Valid UpdateSupplierCmd command);

    /**
     * 删除供应商
     *
     * @param supplierCode 需要删除的供应商的编码
     */
    void remove(SupplierCode supplierCode);

    /**
     * 为供应商添加新的联系人
     *
     * @param supplierCode 添加联系人的供应商的编码
     * @param command      包含需要添加的供应商联系人信息的命令对象
     * @return 返回生成的供应商联系人ID，作为联系人的唯一标识
     */
    SupplierContactId addContact(SupplierCode supplierCode, @Valid AddSupplierContactCmd command);

    /**
     * 更新供应商联系人信息
     *
     * @param supplierCode 需要更新联系人信息的供应商的编码
     * @param command      包含需要更新的供应商联系人信息的命令对象，包括联系人ID和需要修改的信息
     */
    void updateContact(SupplierCode supplierCode, @Valid UpdateSupplierContactCmd command);

    /**
     * 删除供应商联系人
     *
     * @param supplierCode      需要删除联系人的供应商的编码
     * @param supplierContactId 需要删除的供应商联系人的ID
     */
    void removeContact(SupplierCode supplierCode, SupplierContactId supplierContactId);
}
