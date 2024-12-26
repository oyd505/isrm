package com.island.isrm.core.inquiry.port.api;

import com.island.isrm.core.inquiry.application.QuoteAppService;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import com.island.isrm.core.inquiry.port.repo.service.QuoteQueryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报价RESTful API接口
 * 提供与报价相关的HTTP请求处理功能，包括报价请求的创建、查询、更新和删除等操作
 *
 * @author dao.ouyang
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/v1/quote")
public class QuoteRest {
    private final QuoteQueryService quoteQueryService;
    private final QuoteAppService quoteAppService;

    public QuoteRest(QuoteQueryService quoteQueryService, QuoteAppService quoteAppService) {
        this.quoteQueryService = quoteQueryService;
        this.quoteAppService = quoteAppService;
    }

    /**
     * 创建报价请求
     * 供应商根据询价编码创建新的报价请求
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 创建的报价编码
     */
    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping("/request/save")
    public QuoteCode createQuote(@RequestParam String inquiryCode, @RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        return this.quoteAppService.create(new InquiryCode(inquiryCode), new SupplierCode(supplierCode));
    }

    /**
     * 查询报价请求列表
     * 根据询价编码和供应商编码查询相关的报价请求基本信息列表
     *
     * @param inquiryCode  询价编码
     * @param supplierCode 供应商编码
     * @return 报价请求基本信息列表
     */
    @GetMapping("/request/list")
    public List<QuoteRequestBasic> list(@RequestParam String inquiryCode, @RequestParam String supplierCode) {
        return this.quoteQueryService.findBasicByInquiryCodeAndSupplierCode(inquiryCode, supplierCode);
    }

    /**
     * 查询特定报价请求详情
     * 通过报价编码查询报价请求的详细信息
     *
     * @param quoteCode 报价编码
     * @return 报价请求详细信息
     */
    @GetMapping("/request/{quoteCode}")
    public QuoteRequestDO find(@PathVariable String quoteCode) {
        return this.quoteQueryService.findById(quoteCode);
    }

    /**
     * 更新报价请求
     * 更新特定报价请求的信息，根据提供的更新命令对象
     *
     * @param updateQuoteRequestCmd 包含更新信息的命令对象
     */
    @PostMapping("/request/update")
    public void update(@RequestBody UpdateQuoteRequestCmd updateQuoteRequestCmd) {
        this.quoteAppService.update(updateQuoteRequestCmd);
    }

    /**
     * 删除报价请求
     * 通过报价编码删除特定的报价请求
     *
     * @param quoteCode 报价编码
     */
    @PostMapping("/request/delete/{quoteCode}")
    public void delete(@PathVariable String quoteCode) {
        this.quoteAppService.remove(new QuoteCode(quoteCode));
    }

    /**
     * 提交报价请求
     * 通过报价编码提交特定的报价请求，表示报价已完成并可以被评审
     *
     * @param quoteCode 报价编码
     */
    @PostMapping("/request/submit/{quoteCode}")
    public void submit(@PathVariable String quoteCode) {
        this.quoteAppService.submit(new QuoteCode(quoteCode));
    }

    /**
     * 查询报价单项列表
     * 通过报价编码查询报价单项的基本信息列表
     *
     * @param quoteCode 报价编码
     * @return 报价单项基本信息列表
     */
    @GetMapping("/{quoteCode}/item/list")
    public List<QuoteItemBasic> listItem(@PathVariable String quoteCode) {
        return this.quoteQueryService.findItemBasicByQuoteCode(quoteCode);
    }

    /**
     * 查询特定报价单项详情
     * 通过报价编码和报价单项ID查询单项的详细信息
     *
     * @param quoteCode   报价编码
     * @param quoteItemId 报价单项ID
     * @return 报价单项详细信息
     */
    @GetMapping("/{quoteCode}/item/{quoteItemId}")
    public QuoteItemDO findItem(@PathVariable String quoteCode, @PathVariable Long quoteItemId) {
        return this.quoteQueryService.findByQuoteItemIdAndQuoteCode(quoteItemId, quoteCode);
    }

    /**
     * 更新报价单项
     * 更新特定报价单项的信息，根据提供的更新命令对象
     *
     * @param quoteCode          报价编码
     * @param updateQuoteItemCmd 包含更新信息的命令对象
     */
    @PostMapping("/{quoteCode}/item/update")
    public void updateItem(@PathVariable String quoteCode, @RequestBody UpdateQuoteItemCmd updateQuoteItemCmd) {
        this.quoteAppService.updateItem(quoteCode, updateQuoteItemCmd);
    }
}
