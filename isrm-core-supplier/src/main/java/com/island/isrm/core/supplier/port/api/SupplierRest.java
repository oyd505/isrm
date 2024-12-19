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

    @GetMapping("/page/basic")
    public Page<SupplierBasic> pageBasic(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return supplierQueryService.pageBasic(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/list/code/name")
    public List<SupplierCodeAndName> listCodeAndName() {
        return supplierQueryService.listCodeAndName();
    }

    @GetMapping("/{supplierCode}")
    public SupplierDO find(@PathVariable String supplierCode) {
        return supplierQueryService.find(supplierCode);
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateSupplierCmd command) {
        return supplierAppService.create(command).getValue();
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateSupplierCmd command) {
        supplierAppService.update(command);
    }

    @PostMapping("/remove/{supplierCode}")
    public void remove(@PathVariable String supplierCode) {
        supplierAppService.remove(new SupplierCode(supplierCode));
    }

    @GetMapping("/{supplierCode}/contact/list")
    public List<SupplierContactBasic> listContactBasic(@PathVariable String supplierCode) {
        return supplierQueryService.listContactBasic(supplierCode);
    }

    @GetMapping("/{supplierCode}/contact/list/name/phone")
    public List<ContactNameAndPhone> listContact(@PathVariable String supplierCode) {
        return supplierQueryService.listContactNameAndPhone(supplierCode);
    }

    @GetMapping("/{supplierCode}/contact/{supplierContactId}")
    public SupplierContactDO findContact(@PathVariable Long supplierContactId, @PathVariable String supplierCode) {
        return supplierQueryService.findContact(supplierContactId, supplierCode);
    }

    @PostMapping("/{supplierCode}/contact/add")
    public Long addContact(@PathVariable String supplierCode, @RequestBody AddSupplierContactCmd command) {
        return supplierAppService.addContact(new SupplierCode(supplierCode), command).getId();
    }

    @PostMapping("/{supplierCode}/contact/update")
    public void updateContact(@PathVariable String supplierCode, @RequestBody UpdateSupplierContactCmd command) {
        supplierAppService.updateContact(new SupplierCode(supplierCode), command);
    }

    @PostMapping("/{supplierCode}/contact/remove/{supplierContactId}")
    public void removeContact(@PathVariable String supplierCode, @PathVariable Long supplierContactId) {
        supplierAppService.removeContact(new SupplierCode(supplierCode), new SupplierContactId(supplierContactId));
    }
}
