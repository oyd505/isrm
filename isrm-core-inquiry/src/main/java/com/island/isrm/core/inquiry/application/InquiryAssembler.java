package com.island.isrm.core.inquiry.application;

import com.island.isrm.core.common.domain.dp.*;
import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;

public class InquiryAssembler {
    public final static InquiryAssembler instance = new InquiryAssembler();

    public InquiryRequest toAddEntity(CreateInquiryRequestCmd command, InquiryCode inquiryCode, InquiryStatus inquiryStatus) {
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        assemble(command, inquiryRequest);
        return inquiryRequest;
    }

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

    public InquiryRequest toUpdateEntity(UpdateInquiryRequestCmd command, InquiryStatus inquiryStatus) {
        InquiryRequest update = new InquiryRequest(new InquiryCode(command.getInquiryCode()), inquiryStatus);
        assemble(command, update);
        return update;
    }

    public InquiryProduct toAddEntity(AddInquiryProductCmd command) {
        InquiryProduct inquiryProduct = new InquiryProduct(null, new InquiryCode(command.getInquiryCode()));
        assemble(command, inquiryProduct);
        return inquiryProduct;
    }

    private void assemble(AddInquiryProductCmd source, InquiryProduct target) {
        target.setProduct(new Product(source.getCode(), source.getName()));
        target.setQuantity(new Quantity(source.getQuantity(), source.getUnits()));
        target.setFactoryOrg(new FactoryOrg(source.getFactoryCode(), source.getFactoryName()));
        target.setDeliveryDate(new DeliveryDate(source.getDeliveryDate()));
    }

    public InquiryProduct toUpdateEntity(UpdateInquiryProductCmd command) {
        InquiryProduct update = new InquiryProduct(new InquiryProductId(command.getId()), new InquiryCode(command.getInquiryCode()));
        assemble(command, update);
        return update;
    }

    public InquirySupplier toAddEntity(AddInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(null, new InquiryCode(command.getInquiryCode()));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        inquirySupplier.setAudited(Flag.valueOf(command.getAudited()));
        return inquirySupplier;
    }

    public InquirySupplier toApplyEntity(ApplyInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(null, new InquiryCode(command.getInquiryCode()));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        return inquirySupplier;
    }

    public InquirySupplier toUpdateEntity(UpdateInquirySupplierCmd command) {
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(command.getId()), new InquiryCode(command.getInquiryCode()));
        inquirySupplier.setSupplier(new Supplier(new SupplierCode(command.getCode()), command.getName()));
        inquirySupplier.setContact(new Contact(command.getContactName(), command.getContactPhone()));
        inquirySupplier.setAudited(Flag.valueOf(command.getAudited()));
        return inquirySupplier;
    }
}
