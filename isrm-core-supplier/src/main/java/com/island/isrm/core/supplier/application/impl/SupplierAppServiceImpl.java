package com.island.isrm.core.supplier.application.impl;

import com.island.isrm.core.common.domain.event.SupplierContactCreatedEvent;
import com.island.isrm.core.common.domain.event.SupplierCreatedEvent;
import com.island.isrm.core.supplier.application.SupplierAppService;
import com.island.isrm.core.supplier.application.SupplierAssembler;
import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.domain.dp.SupplierStatus;
import com.island.isrm.core.supplier.domain.entity.Supplier;
import com.island.isrm.core.supplier.domain.entity.SupplierContact;
import com.island.isrm.core.supplier.domain.external.SupplierCodeService;
import com.island.isrm.core.supplier.domain.repo.SupplierRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商应用服务实现类
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Service
public class SupplierAppServiceImpl implements SupplierAppService {
    // 供应商组装器实例
    private final SupplierAssembler supplierAssembler = SupplierAssembler.instance;
    // 供应商仓储
    private final SupplierRepository supplierRepository;
    // 供应商编码服务
    private final SupplierCodeService supplierCodeService;
    // 应用事件发布器
    private final ApplicationEventPublisher publisher;

    /**
     * 构造函数注入所需服务和仓储
     */
    public SupplierAppServiceImpl(SupplierRepository supplierRepository, SupplierCodeService supplierCodeService, ApplicationEventPublisher publisher) {
        this.supplierRepository = supplierRepository;
        this.supplierCodeService = supplierCodeService;
        this.publisher = publisher;
    }

    /**
     * 创建供应商
     *
     * @param command 创建供应商命令
     * @return 供应商编码
     */
    @Transactional
    @Override
    public SupplierCode create(CreateSupplierCmd command) {
        // 生成供应商编码
        SupplierCode supplierCode = this.supplierCodeService.generateSerialNumber();
        // 将命令转换为供应商实体
        Supplier supplier = this.supplierAssembler.toAddEntity(command, supplierCode, SupplierStatus.POTENTIAL);
        // 添加到仓储
        this.supplierRepository.add(supplier);
        // 发布事件
        this.publisher.publishEvent(new SupplierCreatedEvent(this, supplierCode.getValue(), supplier.getName().getValue()));
        return supplierCode;
    }

    /**
     * 更新供应商信息
     *
     * @param command 更新供应商命令
     */
    @Transactional
    @Override
    public void update(UpdateSupplierCmd command) {
        // 将命令转换为供应商实体
        Supplier input = this.supplierAssembler.toUpdateEntity(command, SupplierStatus.POTENTIAL);
        // 从仓储中查找供应商
        Supplier supplier = this.supplierRepository.find(input.getSupplierCode());
        // 更新供应商信息
        supplier.update(input);
        // 更新到仓储
        this.supplierRepository.update(supplier);
    }

    /**
     * 移除供应商
     *
     * @param supplierCode 供应商编码
     */
    @Transactional
    @Override
    public void remove(SupplierCode supplierCode) {
        // 从仓储中查找供应商
        Supplier supplier = this.supplierRepository.find(supplierCode);
        // 检查是否可编辑
        supplier.checkEditable();
        // 从仓储中移除
        this.supplierRepository.removeAll(supplierCode);
    }

    /**
     * 添加供应商联系人
     *
     * @param supplierCode 供应商编码
     * @param command      添加供应商联系人命令
     * @return 供应商联系人编码
     */
    @Override
    public SupplierContactId addContact(SupplierCode supplierCode, AddSupplierContactCmd command) {
        // 从仓储中查找供应商
        Supplier supplier = this.supplierRepository.find(supplierCode);
        // 将命令转换为供应商联系人实体
        SupplierContact supplierContact = this.supplierAssembler.toAddEntity(supplierCode, command);
        // 添加联系人到供应商
        supplier.addContact(supplierContact);
        // 保存联系人到仓储并获取联系人编码
        SupplierContactId supplierContactId = this.supplierRepository.addOneSupplierContact(supplier);
        // 发布事件
        this.publisher.publishEvent(new SupplierContactCreatedEvent(this, supplierCode.getValue(),
                supplier.getName().getValue(), command.getPhone(), command.getName()));
        return supplierContactId;
    }

    /**
     * 更新供应商联系人信息
     *
     * @param supplierCode 供应商编码
     * @param command      更新供应商联系人命令
     */
    @Override
    public void updateContact(SupplierCode supplierCode, UpdateSupplierContactCmd command) {
        // 将命令转换为供应商联系人实体
        SupplierContact input = this.supplierAssembler.toUpdateEntity(supplierCode, command);
        // 从仓储中查找供应商联系人
        Supplier supplier = this.supplierRepository.findOneSupplierContact(
                supplierCode, input.getSupplierContactId()
        );
        // 更新联系人信息
        supplier.updateContact(input);
        // 更新到仓储
        this.supplierRepository.updateSupplierContact(supplier);
    }

    /**
     * 移除供应商联系人
     *
     * @param supplierCode      供应商编码
     * @param supplierContactId 供应商联系人编码
     */
    @Override
    public void removeContact(SupplierCode supplierCode, SupplierContactId supplierContactId) {
        // 从仓储中查找供应商联系人
        this.supplierRepository.findOneSupplierContact(supplierCode, supplierContactId);
        // 从仓储中移除联系人
        this.supplierRepository.removeOneSupplierContact(supplierContactId);
    }
}
