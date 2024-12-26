package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.common.domain.dp.Currency;
import com.island.isrm.core.common.domain.dp.Price;
import com.island.isrm.core.common.domain.dp.Quantity;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteItemCmd;
import com.island.isrm.core.inquiry.application.command.UpdateQuoteRequestCmd;
import com.island.isrm.core.inquiry.domain.dp.quote.DeliveryDeadline;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteItemId;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteStatus;
import com.island.isrm.core.inquiry.domain.entity.QuoteItem;
import com.island.isrm.core.inquiry.domain.entity.QuoteRequest;

/**
 * 报价组装器，用于将更新报价的命令转换为报价实体对象
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public class QuoteAssembler {
    // 单例模式，提供全局唯一的报价组装器实例
    public final static QuoteAssembler instance = new QuoteAssembler();

    /**
     * 将更新报价请求的命令转换为报价请求实体对象
     *
     * @param command     更新报价请求的命令，包含需要更新的报价信息
     * @param quoteStatus 报价的状态
     * @return 更新后的报价请求实体对象
     */
    public QuoteRequest toUpdateEntity(UpdateQuoteRequestCmd command, QuoteStatus quoteStatus) {
        // 创建一个新的报价请求对象，初始化报价编码和状态
        QuoteRequest update = new QuoteRequest(new QuoteCode(command.getQuoteCode()), null, quoteStatus, null);
        // 设置报价请求的货币类型
        update.setCurrency(new Currency(command.getCurrency()));
        return update;
    }

    /**
     * 将更新报价项目的命令转换为报价项目实体对象
     *
     * @param quoteCode 报价编码，用于关联报价项目
     * @param command   更新报价项目的命令，包含需要更新的项目信息
     * @return 更新后的报价项目实体对象
     */
    public QuoteItem toUpdateEntity(String quoteCode, UpdateQuoteItemCmd command) {
        // 创建一个新的报价项目对象，初始化报价项目ID和报价编码
        QuoteItem update = new QuoteItem(new QuoteItemId(command.getId()), null, new QuoteCode(quoteCode));
        // 设置报价项目的数量
        update.setQuoteQuantity(new Quantity(command.getQuoteQuantity(), command.getUnits()));
        // 设置报价项目的单价和税率
        update.setPrice(new Price(command.getPrice(), command.getTaxRate()));
        // 设置报价项目的交货截止日期
        update.setDeliveryDeadline(new DeliveryDeadline(command.getDeliveryDeadline()));
        return update;
    }
}
