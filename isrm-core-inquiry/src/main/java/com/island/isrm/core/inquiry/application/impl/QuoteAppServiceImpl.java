package com.island.isrm.core.inquiry.application.impl;

import com.island.isrm.core.common.domain.external.DomainEventPublisher;
import com.island.isrm.core.inquiry.application.QuoteAppService;
import com.island.isrm.core.inquiry.application.QuoteAssembler;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import com.island.isrm.core.inquiry.domain.external.QuoteCodeService;
import com.island.isrm.core.inquiry.domain.external.QuoteInquiryService;
import com.island.isrm.core.inquiry.domain.repo.QuoteRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 报价应用服务实现类
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class QuoteAppServiceImpl implements QuoteAppService {
    // 引用报价组装器实例
    private final QuoteAssembler quoteAssembler = QuoteAssembler.instance;
    // 报价请求仓储
    private final QuoteRequestRepository quoteRequestRepository;
    // 报价编号服务
    private final QuoteCodeService quoteCodeService;
    // 报价问询服务
    private final QuoteInquiryService quoteInquiryService;
    // 域事件发布器
    private final DomainEventPublisher publisher;

    /**
     * 构造函数注入所需服务和仓储
     */
    public QuoteAppServiceImpl(QuoteRequestRepository quoteRequestRepository,
                               QuoteCodeService quoteCodeService,
                               QuoteInquiryService quoteInquiryService, DomainEventPublisher publisher) {
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteCodeService = quoteCodeService;
        this.quoteInquiryService = quoteInquiryService;
        this.publisher = publisher;
    }

    /**
     * 创建报价单，如果存在未提交的报价单则不创建新报价单
     *
     * @param inquiryCode  问询编号
     * @param supplierCode 供应商编号
     * @return 报价编号
     */
    @Transactional
    @Override
    public QuoteCode create(InquiryCode inquiryCode, SupplierCode supplierCode) {
        // 如果已经有未提交状态的报价单，不创建新报价单，返回未提交状态的报价单的报价编号
        Optional<QuoteRequest> pendingQuoteRequest = this.quoteRequestRepository.findOnlyPending(inquiryCode, supplierCode);
        return pendingQuoteRequest.map(QuoteRequest::getQuoteCode).orElseGet(() -> {
            // 生成新的报价编号
            QuoteCode quoteCode = this.quoteCodeService.generateSerialNumber();
            // 创建新的报价请求
            QuoteRequest quoteRequest = this.quoteInquiryService.createQuote(inquiryCode, supplierCode, quoteCode);
            // 保存新的报价请求并返回报价编号
            return this.quoteRequestRepository.addAll(quoteRequest);
        });
    }

    /**
     * 更新报价请求信息
     *
     * @param command 更新报价请求的命令
     */
    @Transactional
    @Override
    public void update(UpdateQuoteRequestCmd command) {
        // 将命令转换为待更新的实体
        QuoteRequest input = this.quoteAssembler.toUpdateEntity(command, QuoteStatus.PENDING);
        // 查找要更新的报价请求
        QuoteRequest quoteRequest = this.quoteRequestRepository.find(input.getQuoteCode());
        // 更新报价请求信息
        quoteRequest.update(input);
        // 保存更新后的报价请求
        this.quoteRequestRepository.update(quoteRequest);
    }

    /**
     * 删除报价请求
     *
     * @param quoteCode 报价编号
     */
    @Transactional
    @Override
    public void remove(QuoteCode quoteCode) {
        // 查找要删除的报价请求
        QuoteRequest quoteRequest = this.quoteRequestRepository.find(quoteCode);
        // 检查报价请求是否可编辑
        quoteRequest.checkEditable();
        // 删除报价请求
        this.quoteRequestRepository.removeAll(quoteCode);
    }

    /**
     * 提交报价请求
     *
     * @param quoteCode 报价编号
     */
    @Transactional
    @Override
    public void submit(QuoteCode quoteCode) {
        // 查找要提交的报价请求
        QuoteRequest quoteRequest = this.quoteRequestRepository.findAll(quoteCode);
        // 检查报价请求是否有效
        this.quoteInquiryService.checkQuoteEnabled(quoteRequest.getInquiryCode(), quoteRequest.getSupplier().getCode());
        // 提交报价请求
        quoteRequest.submit();
        // 失效老版本,确保只有一个生效报价
        Optional<QuoteRequest> submittedQuoteRequest = this.quoteRequestRepository.findOnlySubmitted(
                quoteRequest.getInquiryCode(), quoteRequest.getSupplier().getCode());
        submittedQuoteRequest.ifPresent(quote -> {
            quote.cancel();
            this.quoteRequestRepository.update(quote);
        });
        // 保存提交后的报价请求
        this.quoteRequestRepository.update(quoteRequest);
        // 发送报价已提交的事件
        this.publisher.publishEvent(new QuoteSubmittedEvent(this, quoteCode.getValue(),
                quoteRequest.getSupplier().getCode().getValue(), quoteRequest.getInquiryCode().getValue()));
    }

    /**
     * 更新报价项目信息
     *
     * @param quoteCode 报价编号
     * @param command   更新报价项目的命令
     */
    @Transactional
    @Override
    public void updateItem(String quoteCode, UpdateQuoteItemCmd command) {
        // 将命令转换为待更新的实体
        QuoteItem input = this.quoteAssembler.toUpdateEntity(quoteCode, command);
        // 查找包含报价项目的报价请求
        QuoteRequest quoteRequest = this.quoteRequestRepository.findOneQuoteItem(
                input.getQuoteCode(), input.getQuoteItemId()
        );
        // 更新报价项目信息
        quoteRequest.updateQuoteItem(input);
        // 保存更新后的报价请求
        this.quoteRequestRepository.updateQuoteItem(quoteRequest);
    }
}
