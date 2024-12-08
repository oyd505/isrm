package com.island.isrm.core.inquiry.port.api;

import com.island.isrm.core.inquiry.application.QuoteAppService;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteItemDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.QuoteRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteItemBasic;
import com.island.isrm.core.inquiry.port.repo.dao.projection.QuoteRequestBasic;
import com.island.isrm.core.inquiry.port.repo.service.QuoteQueryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/quote")
public class QuoteRest {
    private final QuoteQueryService quoteQueryService;
    private final QuoteAppService quoteAppService;

    public QuoteRest(QuoteQueryService quoteQueryService, QuoteAppService quoteAppService) {
        this.quoteQueryService = quoteQueryService;
        this.quoteAppService = quoteAppService;
    }

    @PreAuthorize("hasRole('QUOTE')")
    @PostMapping("/request/save")
    public QuoteCode createQuote(@RequestParam String inquiryCode, @RequestParam String supplierCode) {
        // TODO 检验用户的供应商身份, 确保是供应商亲自操作
        return this.quoteAppService.create(new InquiryCode(inquiryCode), new SupplierCode(supplierCode));
    }

    @GetMapping("/request/list")
    public List<QuoteRequestBasic> list(@RequestParam String inquiryCode, @RequestParam String supplierCode) {
        return this.quoteQueryService.findBasicByInquiryCodeAndSupplierCode(inquiryCode, supplierCode);
    }

    @GetMapping("/request/{quoteCode}")
    public QuoteRequestDO find(@PathVariable String quoteCode) {
        return this.quoteQueryService.findById(quoteCode);
    }

    @PostMapping("/request/update")
    public void update(@RequestBody UpdateQuoteRequestCmd updateQuoteRequestCmd) {
        this.quoteAppService.update(updateQuoteRequestCmd);
    }

    @PostMapping("/request/delete/{quoteCode}")
    public void delete(@PathVariable String quoteCode) {
        this.quoteAppService.remove(new QuoteCode(quoteCode));
    }

    @PostMapping("/request/submit/{quoteCode}")
    public void submit(@PathVariable String quoteCode) {
        this.quoteAppService.submit(new QuoteCode(quoteCode));
    }

    @GetMapping("/{quoteCode}/item/list")
    public List<QuoteItemBasic> listItem(@PathVariable String quoteCode) {
        return this.quoteQueryService.findItemBasicByQuoteCode(quoteCode);
    }

    @GetMapping("/{quoteCode}/item/{quoteItemId}")
    public QuoteItemDO findItem(@PathVariable String quoteCode, @PathVariable Long quoteItemId) {
        return this.quoteQueryService.findByQuoteItemIdAndQuoteCode(quoteItemId, quoteCode);
    }

    @PostMapping("/{quoteCode}/item/update")
    public void updateItem(@PathVariable String quoteCode, @RequestBody UpdateQuoteItemCmd updateQuoteItemCmd) {
        this.quoteAppService.updateItem(quoteCode, updateQuoteItemCmd);
    }
}
