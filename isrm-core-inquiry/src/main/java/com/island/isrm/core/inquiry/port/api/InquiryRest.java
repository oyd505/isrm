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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 询价单接口
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
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

    /**
     * 询价单列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页数量
     * @return 询价单列表
     */
    @GetMapping("/request/list")
    public Page<InquiryRequestBasic> list(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return this.inquiryQueryService.findBasicOrderByLastModifiedDateDesc(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * 供应商询价单列表
     *
     * @param supplierCode 供应商编码
     * @return 供应商询价单列表
     */
    @GetMapping("/request/list/quote")
    public List<InquiryRequestBasic> listForQuote(@RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("登录用户: {}, {}", authentication.getName(), authentication);
        log.info("{} - {}", authentication.getCredentials(), authentication.getPrincipal());
        return this.inquiryQueryService.findBasicForQuoteOrderByLastModifiedDateDesc(supplierCode);
    }

    /**
     * 询价单详情
     *
     * @param inquiryCode 询价单编码
     * @return 询价单详情
     */
    @GetMapping("/request/{inquiryCode}")
    public InquiryRequestDO find(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findById(inquiryCode);
    }

    /**
     * 创建询价单
     *
     * @param command 询价单创建命令
     * @return 询价单编码
     */
    @PostMapping("/request/save")
    public InquiryCode save(@RequestBody CreateInquiryRequestCmd command) {
        return this.inquiryAppService.create(command);
    }

    /**
     * 更新询价单
     *
     * @param command 更新询价单命令
     */
    @PostMapping("/request/update")
    public void update(@RequestBody UpdateInquiryRequestCmd command) {
        this.inquiryAppService.update(command);
    }

    /**
     * 删除询价单
     *
     * @param inquiryCode 询价单编码
     */
    @PostMapping("/request/delete/{inquiryCode}")
    public void delete(@PathVariable String inquiryCode) {
        this.inquiryAppService.remove(new InquiryCode(inquiryCode));
    }

    /**
     * 提交询价单
     *
     * @param inquiryCode 询价单编码
     */
    @PostMapping("/request/submit/{inquiryCode}")
    public void submit(@PathVariable String inquiryCode) {
        this.inquiryAppService.submit(new InquiryCode(inquiryCode));
    }

    /**
     * 发布询价单
     *
     * @param inquiryCode 询价单编码
     */
    @PostMapping("/request/publish/{inquiryCode}")
    public void publish(@PathVariable String inquiryCode) {
        this.inquiryAppService.publish(new InquiryCode(inquiryCode));
    }

    /**
     * 询价单商品列表
     *
     * @param inquiryCode 询价单编码
     * @return 询价单商品列表
     */
    @GetMapping("/{inquiryCode}/product/list")
    public List<InquiryProductBasic> listProduct(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findProductBasicByInquiryCode(inquiryCode);
    }

    /**
     * 询价单商品详情
     *
     * @param inquiryCode      询价单编码
     * @param inquiryProductId 询价单商品编码
     * @return 询价单商品详情
     */
    @GetMapping("/{inquiryCode}/product/{inquiryProductId}")
    public InquiryProductDO findProduct(@PathVariable String inquiryCode, @PathVariable Long inquiryProductId) {
        return this.inquiryQueryService.findByInquiryProductIdAndInquiryCode(inquiryProductId, inquiryCode);
    }

    /**
     * 添加询价单商品
     *
     * @param inquiryCode          询价单编码
     * @param addInquiryProductCmd 添加询价单商品命令
     * @return 询价单商品编码
     */
    @PreAuthorize("hasRole('BUYER')")
    @PostMapping("/{inquiryCode}/product/save")
    public Long saveProduct(@PathVariable String inquiryCode, @RequestBody AddInquiryProductCmd addInquiryProductCmd) {
        return this.inquiryAppService.addProduct(inquiryCode, addInquiryProductCmd);
    }

    /**
     * 更新询价单商品
     *
     * @param inquiryCode             询价单编码
     * @param updateInquiryProductCmd 更新询价单商品命令
     */
    @PostMapping("/{inquiryCode}/product/update")
    public void updateProduct(@PathVariable String inquiryCode, @RequestBody UpdateInquiryProductCmd updateInquiryProductCmd) {
        this.inquiryAppService.updateProduct(inquiryCode, updateInquiryProductCmd);
    }

    /**
     * 删除询价单商品
     *
     * @param inquiryCode      询价单编码
     * @param inquiryProductId 询价单商品编码
     */
    @PostMapping("/{inquiryCode}/product/delete/{inquiryProductId}")
    public void deleteProduct(@PathVariable String inquiryCode, @PathVariable Long inquiryProductId) {
        this.inquiryAppService.removeProduct(new InquiryCode(inquiryCode), new InquiryProductId(inquiryProductId));
    }

    /**
     * 供应商列表
     *
     * @param inquiryCode 询价单编码
     * @return 供应商列表
     */
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    @GetMapping("/{inquiryCode}/supplier/list")
    public List<InquirySupplierBasic> listSupplier(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findSupplierBasicByInquiryCode(inquiryCode);
    }

    /**
     * 供应商报价单列表
     *
     * @param inquiryCode 询价单编码
     * @return 供应商报价单列表
     */
    @GetMapping("/{inquiryCode}/supplier/quote/list")
    public List<InquirySupplierQuote> listSupplierQuote(@PathVariable String inquiryCode) {
        return this.inquiryQueryService.findSupplierQuoteByInquiryCode(inquiryCode);
    }

    /**
     * 询价供应商详情
     *
     * @param inquiryCode       询价单编码
     * @param inquirySupplierId 询价供应商编码
     * @return 询价供应商详情
     */
    @GetMapping("/{inquiryCode}/supplier/{inquirySupplierId}")
    public InquirySupplierDO findSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        return this.inquiryQueryService.findByInquirySupplierIdAndInquiryCode(inquirySupplierId, inquiryCode);
    }

    /**
     * 询价供应商详情
     *
     * @param inquiryCode  询价单编码
     * @param supplierCode 供应商编码
     * @return 询价供应商详情
     */
    @GetMapping("/{inquiryCode}/supplier/detail")
    public InquirySupplierDO findSupplier(@PathVariable String inquiryCode, @RequestParam String supplierCode) {
        return this.inquiryQueryService.findByInquiryCodeAndCode(inquiryCode, supplierCode);
    }

    /**
     * 添加询价供应商
     *
     * @param inquiryCode           询价单编码
     * @param addInquirySupplierCmd 添加询价供应商命令
     * @return 询价供应商编码
     */
    @PostMapping("/{inquiryCode}/supplier/save")
    public Long inviteSupplier(@PathVariable String inquiryCode, @RequestBody AddInquirySupplierCmd addInquirySupplierCmd) {
        return this.inquiryAppService.inviteSupplier(inquiryCode, addInquirySupplierCmd);
    }

    /**
     * 供应商申请参与询价
     *
     * @param inquiryCode             询价单编码
     * @param applyInquirySupplierCmd 申请询价供应商命令
     * @return 询价供应商ID
     */
    @PostMapping("/{inquiryCode}/supplier/apply")
    public Long applySupplier(@PathVariable String inquiryCode, @RequestBody ApplyInquirySupplierCmd applyInquirySupplierCmd) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        return this.inquiryAppService.applySupplier(inquiryCode, applyInquirySupplierCmd);
    }

    /**
     * 供应商确认报价邀请
     *
     * @param inquiryCode  询价单编码
     * @param supplierCode 供应商编码
     */
    @PostMapping("/{inquiryCode}/supplier/confirm")
    public void confirmInvitation(@PathVariable String inquiryCode, @RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        this.inquiryAppService.confirmInvitation(new InquiryCode(inquiryCode), new SupplierCode(supplierCode));
    }

    /**
     * 审核供应商的参与资格
     *
     * @param inquiryCode       询价单编码
     * @param inquirySupplierId 询价供应商ID
     */
    @PostMapping("/{inquiryCode}/supplier/audit/{inquirySupplierId}")
    public void auditSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        this.inquiryAppService.auditSupplier(new InquiryCode(inquiryCode), new InquirySupplierId(inquirySupplierId));
    }

    /**
     * 更新询价供应商
     *
     * @param inquiryCode              询价单编码
     * @param updateInquirySupplierCmd 更新询价供应商命令
     */
    @PostMapping("/{inquiryCode}/supplier/update")
    public void updateSupplier(@PathVariable String inquiryCode, @RequestBody UpdateInquirySupplierCmd updateInquirySupplierCmd) {
        this.inquiryAppService.updateSupplier(inquiryCode, updateInquirySupplierCmd);
    }

    /**
     * 删除询价供应商
     *
     * @param inquiryCode       询价单编码
     * @param inquirySupplierId 询价供应商ID
     */
    @PostMapping("/{inquiryCode}/supplier/delete/{inquirySupplierId}")
    public void deleteSupplier(@PathVariable String inquiryCode, @PathVariable Long inquirySupplierId) {
        this.inquiryAppService.removeSupplier(new InquiryCode(inquiryCode), new InquirySupplierId(inquirySupplierId));
    }
}
