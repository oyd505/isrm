package com.island.isrm.core.inquiry.port.api;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryProductId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquirySupplierId;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryProductBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryRequestBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquirySupplierQuote;
import com.island.isrm.core.inquiry.port.repo.service.InquiryQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/inquiry")
public class InquiryRest {
    private final InquiryQueryService inquiryQueryService;
    private final InquiryAppService inquiryAppService;

    public InquiryRest(InquiryQueryService inquiryQueryService, InquiryAppService inquiryAppService) {
        this.inquiryQueryService = inquiryQueryService;
        this.inquiryAppService = inquiryAppService;
    }

    @GetMapping("/request/list")
    public List<InquiryRequestBasic> list() {
        return this.inquiryQueryService.findBasicOrderByLastModifiedDateDesc();
    }

    @GetMapping("/request/list/quote")
    public List<InquiryRequestBasic> listForQuote(@RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("登录用户: {}, {}", authentication.getName(), authentication);
        log.info("{} - {}", authentication.getCredentials(), authentication.getPrincipal());
        return this.inquiryQueryService.findBasicForQuoteOrderByLastModifiedDateDesc(supplierCode);
    }

    @GetMapping("/request/{inquiryCode}")
    public InquiryRequestDO find(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findById(inquiryCode);
    }

    @PostMapping("/request/save")
    public InquiryCode save(@RequestBody CreateInquiryRequestCmd createInquiryRequestCmd) {
        return this.inquiryAppService.create(createInquiryRequestCmd);
    }

    @PostMapping("/request/update")
    public void update(@RequestBody UpdateInquiryRequestCmd updateInquiryRequestCmd) {
        this.inquiryAppService.update(updateInquiryRequestCmd);
    }

    @PostMapping("/request/delete/{inquiryCode}")
    public void delete(@PathVariable String inquiryCode) {
        this.inquiryAppService.remove(new InquiryCode(inquiryCode));
    }

    @PostMapping("/request/submit/{inquiryCode}")
    public void submit(@PathVariable String inquiryCode) {
        this.inquiryAppService.submit(new InquiryCode(inquiryCode));
    }

    @PostMapping("/request/publish/{inquiryCode}")
    public void publish(@PathVariable String inquiryCode) {
        this.inquiryAppService.publish(new InquiryCode(inquiryCode));
    }

    @GetMapping("/{inquiryCode}/product/list")
    public List<InquiryProductBasic> listProduct(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findProductBasicByInquiryCode(inquiryCode);
    }

    @GetMapping("/{inquiryCode}/product/{inquiryProductId}")
    public InquiryProductDO findProduct(@PathVariable String inquiryCode, @PathVariable Long inquiryProductId) {
        return this.inquiryQueryService.findByInquiryProductIdAndInquiryCode(inquiryProductId, inquiryCode);
    }

    @PreAuthorize("hasRole('INQUIRY')")
    @PostMapping("/{inquiryCode}/product/save")
    public Long saveProduct(@PathVariable String inquiryCode, @RequestBody AddInquiryProductCmd addInquiryProductCmd) {
        addInquiryProductCmd.setInquiryCode(inquiryCode);
        return this.inquiryAppService.addProduct(addInquiryProductCmd);
    }

    @PostMapping("/{inquiryCode}/product/update")
    public void updateProduct(@RequestBody UpdateInquiryProductCmd updateInquiryProductCmd) {
        this.inquiryAppService.updateProduct(updateInquiryProductCmd);
    }

    @PostMapping("/{inquiryCode}/product/delete/{inquiryProductId}")
    public void deleteProduct(@PathVariable String inquiryCode, @PathVariable Long inquiryProductId) {
        this.inquiryAppService.removeProduct(new InquiryCode(inquiryCode), new InquiryProductId(inquiryProductId));
    }

    @PreAuthorize("hasAuthority('ROLE_INQUIRY')")
    @GetMapping("/{inquiryCode}/supplier/list")
    public List<InquirySupplierBasic> listSupplier(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findSupplierBasicByInquiryCode(inquiryCode);
    }

    @GetMapping("/{inquiryCode}/supplier/quote/list")
    public List<InquirySupplierQuote> listSupplierQuote(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findSupplierQuoteByInquiryCode(inquiryCode);
    }

    @GetMapping("/{inquiryCode}/supplier/{inquirySupplierId}")
    public InquirySupplierDO findSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        return this.inquiryQueryService.findByInquirySupplierIdAndInquiryCode(inquirySupplierId, inquiryCode);
    }

    @GetMapping("/{inquiryCode}/supplier/detail")
    public InquirySupplierDO findSupplier(@PathVariable String inquiryCode, @RequestParam String supplierCode) {
        return this.inquiryQueryService.findByInquiryCodeAndCode(inquiryCode, supplierCode);
    }

    @PostMapping("/{inquiryCode}/supplier/save")
    public Long inviteSupplier(@PathVariable String inquiryCode, @RequestBody AddInquirySupplierCmd addInquirySupplierCmd) {
        addInquirySupplierCmd.setInquiryCode(inquiryCode);
        return this.inquiryAppService.inviteSupplier(addInquirySupplierCmd);
    }

    @PostMapping("/{inquiryCode}/supplier/apply")
    public Long applySupplier(@PathVariable String inquiryCode, @RequestBody ApplyInquirySupplierCmd applyInquirySupplierCmd) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        applyInquirySupplierCmd.setInquiryCode(inquiryCode);
        return this.inquiryAppService.applySupplier(applyInquirySupplierCmd);
    }

    @PostMapping("/{inquiryCode}/supplier/confirm")
    public void confirmInvitation(@PathVariable String inquiryCode, @RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        this.inquiryAppService.confirmInvitation(new InquiryCode(inquiryCode), new SupplierCode(supplierCode));
    }

    @PostMapping("/{inquiryCode}/supplier/audit/{inquirySupplierId}")
    public void auditSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        this.inquiryAppService.auditSupplier(new InquiryCode(inquiryCode), new InquirySupplierId(inquirySupplierId));
    }

    @PostMapping("/{inquiryCode}/supplier/update")
    public void updateSupplier(@RequestBody UpdateInquirySupplierCmd updateInquirySupplierCmd) {
        this.inquiryAppService.updateSupplier(updateInquirySupplierCmd);
    }

    @PostMapping("/{inquiryCode}/supplier/delete/{inquirySupplierId}")
    public void deleteSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        this.inquiryAppService.removeSupplier(new InquiryCode(inquiryCode), new InquirySupplierId(inquirySupplierId));
    }
}
