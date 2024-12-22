package com.island.isrm.core.supplier.port.api;

import com.island.isrm.core.supplier.application.SupplierAppService;
import com.island.isrm.core.supplier.application.command.AddSupplierContactCmd;
import com.island.isrm.core.supplier.application.command.CreateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierCmd;
import com.island.isrm.core.supplier.application.command.UpdateSupplierContactCmd;
import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.dp.SupplierContactId;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierContactDO;
import com.island.isrm.core.supplier.port.repo.dao.dataobject.SupplierDO;
import com.island.isrm.core.supplier.port.repo.dao.projection.ContactNameAndPhone;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierBasic;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierCodeAndName;
import com.island.isrm.core.supplier.port.repo.dao.projection.SupplierContactBasic;
import com.island.isrm.core.supplier.port.repo.service.SupplierQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SupplierRest类提供了供应商管理的RESTful API接口
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@Slf4j
@RestController
@RequestMapping("/v1/supplier")
public class SupplierRest {
    private final SupplierQueryService supplierQueryService;
    private final SupplierAppService supplierAppService;

    public SupplierRest(SupplierQueryService supplierQueryService, SupplierAppService supplierAppService) {
        this.supplierQueryService = supplierQueryService;
        this.supplierAppService = supplierAppService;
    }

    /**
     * 分页获取供应商基本信息
     *
     * @param pageNumber 页码
     * @param pageSize   每页大小
     * @return 供应商基本信息的分页结果
     */
    @GetMapping("/page/basic")
    public Page<SupplierBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return supplierQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 获取所有供应商的编码和名称列表
     *
     * @return 供应商编码和名称的列表
     */
    @GetMapping("/list/code/name")
    public List<SupplierCodeAndName> listCodeAndName() {
        return supplierQueryService.listCodeAndName();
    }

    /**
     * 根据供应商编码查找供应商详细信息
     *
     * @param supplierCode 供应商编码
     * @return 供应商详细信息
     */
    @GetMapping("/{supplierCode}")
    public SupplierDO find(@PathVariable String supplierCode) {
        return supplierQueryService.find(supplierCode);
    }

    /**
     * 创建新供应商
     *
     * @param command 创建供应商的命令对象
     * @return 新创建供应商的编码
     */
    @PostMapping("/create")
    public String create(@RequestBody CreateSupplierCmd command) {
        return supplierAppService.create(command).getValue();
    }

    /**
     * 更新供应商信息
     *
     * @param command 更新供应商的命令对象
     */
    @PostMapping("/update")
    public void update(@RequestBody UpdateSupplierCmd command) {
        supplierAppService.update(command);
    }

    /**
     * 删除供应商
     *
     * @param supplierCode 供应商编码
     */
    @PostMapping("/remove/{supplierCode}")
    public void remove(@PathVariable String supplierCode) {
        supplierAppService.remove(new SupplierCode(supplierCode));
    }

    /**
     * 获取供应商的联系人基本信息列表
     *
     * @param supplierCode 供应商编码
     * @return 供应商联系人基本信息列表
     */
    @GetMapping("/{supplierCode}/contact/list")
    public List<SupplierContactBasic> listContactBasic(@PathVariable String supplierCode) {
        return supplierQueryService.listContactBasic(supplierCode);
    }

    /**
     * 获取供应商的联系人姓名和电话列表
     *
     * @param supplierCode 供应商编码
     * @return 供应商联系人姓名和电话列表
     */
    @GetMapping("/{supplierCode}/contact/list/name/phone")
    public List<ContactNameAndPhone> listContact(@PathVariable String supplierCode) {
        return supplierQueryService.listContactNameAndPhone(supplierCode);
    }

    /**
     * 根据联系人ID和供应商编码查找联系人详细信息
     *
     * @param supplierContactId 联系人ID
     * @param supplierCode      供应商编码
     * @return 联系人详细信息
     */
    @GetMapping("/{supplierCode}/contact/{supplierContactId}")
    public SupplierContactDO findContact(@PathVariable Long supplierContactId, @PathVariable String supplierCode) {
        return supplierQueryService.findContact(supplierContactId, supplierCode);
    }

    /**
     * 为供应商添加新联系人
     *
     * @param supplierCode 供应商编码
     * @param command      添加供应商联系人的命令对象
     * @return 新添加联系人的ID
     */
    @PostMapping("/{supplierCode}/contact/add")
    public Long addContact(@PathVariable String supplierCode, @RequestBody AddSupplierContactCmd command) {
        return supplierAppService.addContact(new SupplierCode(supplierCode), command).getId();
    }

    /**
     * 更新供应商联系人信息
     *
     * @param supplierCode 供应商编码
     * @param command      更新供应商联系人的命令对象
     */
    @PostMapping("/{supplierCode}/contact/update")
    public void updateContact(@PathVariable String supplierCode, @RequestBody UpdateSupplierContactCmd command) {
        supplierAppService.updateContact(new SupplierCode(supplierCode), command);
    }

    /**
     * 删除供应商联系人
     *
     * @param supplierCode      供应商编码
     * @param supplierContactId 联系人ID
     */
    @PostMapping("/{supplierCode}/contact/remove/{supplierContactId}")
    public void removeContact(@PathVariable String supplierCode, @PathVariable Long supplierContactId) {
        supplierAppService.removeContact(new SupplierCode(supplierCode), new SupplierContactId(supplierContactId));
    }
}
