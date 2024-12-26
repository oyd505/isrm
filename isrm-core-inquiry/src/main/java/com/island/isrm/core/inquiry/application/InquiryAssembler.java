package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.common.domain.dp.*;
import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;

/**
 * InquiryAssembler类负责将命令对象转换为实体对象
 * 它提供了用于创建、更新询价请求、商品和供应商实体的方法
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
public class InquiryAssembler {
    // 单例模式，确保只有一个InquiryAssembler实例
    public final static InquiryAssembler instance = new InquiryAssembler();

    /**
     * 将创建询价请求的命令转换为实体对象
     *
     * @param command       创建询价请求的命令对象，包含所有必要信息
     * @param inquiryCode   询价请求的编码
     * @param inquiryStatus 询价请求的状态
     * @return 返回一个初始化的InquiryRequest对象
     */
    public InquiryRequest toAddEntity(CreateInquiryRequestCmd command, InquiryCode inquiryCode, InquiryStatus inquiryStatus) {
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        assemble(command, inquiryRequest);
        return inquiryRequest;
    }

    /**
     * 具体组装InquiryRequest实体的方法
     *
     * @param source 命令对象，作为数据源
     * @param target 待填充的InquiryRequest实体对象
     */
    private void assemble(CreateInquiryRequestCmd source, InquiryRequest target) {
        target.setTitle(new Title(source.getTitle()));
        target.setParticipation(Participation.valueOf(source.getParticipation()));
        target.setBuyer(new Buyer(source.getBuyerCode(), source.getBuyerName()));
        target.setReason(new Reason(source.getReason()));
        target.setQuoteTime(new QuoteTime(source.getStartTime(), source.getEndTime()));
        target.setCurrency(new Currency(source.getCurrency()));
        target.setPaymentTerms(new PaymentTerms(source.getPaymentTerms()));
        target.setPurchasingOrg(new PurchasingOrg(source.getPurOrgCode(), source.getPurOrgName()));
    }

    /**
     * 将更新询价请求的命令转换为实体对象
     *
     * @param command       更新询价请求的命令对象
     * @param inquiryStatus 询价请求的最新状态
     * @return 返回一个更新后的InquiryRequest对象
     */
    public InquiryRequest toUpdateEntity(UpdateInquiryRequestCmd command, InquiryStatus inquiryStatus) {
        InquiryRequest update = new InquiryRequest(new InquiryCode(command.getInquiryCode()), inquiryStatus);
        assemble(command, update);
        return update;
    }

    /**
     * 将添加询价商品的命令转换为实体对象
     *
     * @param inquiryCode 询价请求的编码
     * @param command     添加询价商品的命令对象
     * @return 返回一个初始化的InquiryProduct对象
     */
    public InquiryProduct toAddEntity(String inquiryCode, AddInquiryProductCmd command) {
        InquiryProduct inquiryProduct = new InquiryProduct(null, new InquiryCode(inquiryCode));
        assemble(command, inquiryProduct);
        return inquiryProduct;
    }

    /**
     * 具体组装InquiryProduct实体的方法
     *
     * @param source 命令对象，作为数据源
     * @param target 待填充的InquiryProduct实体对象
     */
    private void assemble(AddInquiryProductCmd source, InquiryProduct target) {
        target.setProduct(new Product(source.getCode(), source.getName()));
        target.setQuantity(new Quantity(source.getQuantity(), source.getUnits()));
        target.setFactoryOrg(new FactoryOrg(source.getFactoryCode(), source.getFactoryName()));
        target.setDeliveryDate(new DeliveryDate(source.getDeliveryDate()));
    }

    /**
     * 将更新询价商品的命令转换为实体对象
     *
     * @param inquiryCode 询价请求的编码
     * @param command     更新询价商品的命令对象
     * @return 返回一个更新后的InquiryProduct对象
     */
    public InquiryProduct toUpdateEntity(String inquiryCode, UpdateInquiryProductCmd command) {
        InquiryProduct update = new InquiryProduct(new InquiryProductId(command.getId()), new InquiryCode(inquiryCode));
        assemble(command, update);
        return update;
    }

    /**
     * 将添加询价供应商的命令转换为实体对象
     *
     * @param inquiryCode 询价请求的编码
     * @param command     添加询价供应商的命令对象
     * @return 返回一个初始化的InquirySupplier对象
     */
    public InquirySupplier toAddEntity(String inquiryCode, AddInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(null, new InquiryCode(inquiryCode));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        inquirySupplier.setAudited(Flag.valueOf(command.getAudited()));
        return inquirySupplier;
    }

    /**
     * 将申请加入询价的供应商命令转换为实体对象
     *
     * @param inquiryCode 询价请求的编码
     * @param command     供应商申请加入询价的命令对象
     * @return 返回一个初始化的InquirySupplier对象，用于审核
     */
    public InquirySupplier toApplyEntity(String inquiryCode, ApplyInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(null, new InquiryCode(inquiryCode));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        return inquirySupplier;
    }

    /**
     * 将更新询价供应商的命令转换为实体对象
     *
     * @param inquiryCode 询价请求的编码
     * @param command     更新询价供应商的命令对象
     * @return 返回一个更新后的InquirySupplier对象
     */
    public InquirySupplier toUpdateEntity(String inquiryCode, UpdateInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(command.getId()), new InquiryCode(inquiryCode));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        inquirySupplier.setAudited(Flag.valueOf(command.getAudited()));
        return inquirySupplier;
    }
}
