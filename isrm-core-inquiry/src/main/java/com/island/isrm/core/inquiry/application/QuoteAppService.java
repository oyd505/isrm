package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.SupplierCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 报价应用服务接口，定义了与报价相关的应用层操作。
 * 该接口主要负责处理报价的创建、更新、删除和提交等业务操作。
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Validated
public interface QuoteAppService {

    /**
     * 创建一个新的报价。
     *
     * @param inquiryCode  询价单编码，用于关联询价单。
     * @param supplierCode 供应商编码，标识报价的供应商。
     * @return 返回新创建的报价编码。
     */
    QuoteCode create(InquiryCode inquiryCode, SupplierCode supplierCode);

    /**
     * 更新报价信息。
     *
     * @param command 包含更新信息的命令对象，用于校验输入数据的合法性。
     */
    void update(@Valid UpdateQuoteRequestCmd command);

    /**
     * 删除指定的报价。
     *
     * @param quoteCode 要删除的报价编码。
     */
    void remove(QuoteCode quoteCode);

    /**
     * 提交报价，使其进入下一个处理阶段。
     *
     * @param quoteCode 要提交的报价编码。
     */
    void submit(QuoteCode quoteCode);

    /**
     * 更新报价中的某个项目信息。
     *
     * @param quoteCode 报价编码，用于定位报价。
     * @param command   包含更新信息的命令对象。
     */
    void updateItem(String quoteCode, UpdateQuoteItemCmd command);
}
