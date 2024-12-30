package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.common.domain.dp.*;
import com.island.isrm.core.inquiry.TestHelper;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryRequestDO;
import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquirySupplierDO;
import com.island.isrm.core.inquiry.port.repo.service.dto.InquirySupplierProducts;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
public class InquiryRequestConverterTest {

    @Test
    public void converterInquiryRequests() {
        InquiryRequest inquiryRequest = TestHelper.createInquiryRequest();
        inquiryRequest.setVersion(new Version(1));
        inquiryRequest.setCreatedBy(new CreatedBy("1"));
        inquiryRequest.setCreatedDate(new CreatedDate(LocalDateTime.now()));
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endTime = currentTime.plusHours(1);
        inquiryRequest.setQuoteTime(new QuoteTime(currentTime, endTime));
        inquiryRequest.setPaymentTerms(new PaymentTerms("15 days"));
        inquiryRequest.setCurrency(new Currency("USD"));
        inquiryRequest.setPurchasingOrg(new PurchasingOrg("ISRM", "ISRM"));
        InquiryRequestDO inquiryRequestDO = InquiryRequestConverter.INSTANCE.fromEntity(inquiryRequest);
        log.info("{}", inquiryRequestDO);
        assertNotNull(inquiryRequestDO);
        assertEquals(inquiryRequest.getInquiryCode().getValue(), inquiryRequestDO.getInquiryCode());
        assertEquals(inquiryRequest.getTitle().getValue(), inquiryRequestDO.getTitle());
        assertEquals(inquiryRequest.getReason().getValue(), inquiryRequestDO.getReason());
        assertEquals(inquiryRequest.getBuyer().getCode(), inquiryRequestDO.getBuyerCode());
        assertEquals(inquiryRequest.getBuyer().getName(), inquiryRequestDO.getBuyerName());
        assertEquals(inquiryRequest.getInquiryStatus().name(), inquiryRequestDO.getStatus());

        InquiryRequest copyEntity = InquiryRequestConverter.INSTANCE.toEntity(inquiryRequestDO);
        log.info("{}", inquiryRequest);
        log.info("{}", copyEntity);
        assertEquals(inquiryRequest, copyEntity);
    }

    @Test
    public void nullTest() {
        InquiryRequestDO iro = new InquiryRequestDO();
        iro.setInquiryCode("xxx");
        iro.setStatus(InquiryStatus.PENDING.name());
        iro.setVersion(0);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endTime = currentTime.plusHours(1);
        iro.setStartTime(currentTime);
        iro.setEndTime(endTime);
        InquiryRequest copyEntity = InquiryRequestConverter.INSTANCE.toEntity(iro);
        log.info("{}", copyEntity);
        assertNotNull(copyEntity);
        Optional.of(copyEntity).map(InquiryRequest::getParticipation).map(p -> {
            if (p.isOpen()) {
                log.info("is open");
            } else {
                log.info("not open");
            }
            return p.isOpen();
        }).orElseGet(() -> {
            log.info("is null");
            return false;
        });
        InquiryRequestDO inquiryRequestDO = InquiryRequestConverter.INSTANCE.fromEntity(null);
        log.info("{}", inquiryRequestDO);
        assertNull(inquiryRequestDO);
        InquiryRequestConverter.INSTANCE.fromEntity(new InquiryRequest(new InquiryCode("xx"), InquiryStatus.PENDING));
    }

    @Test
    public void converterInquirySupplierProducts() {
        InquiryRequestDO inquiryRequestDO = new InquiryRequestDO();
        inquiryRequestDO.setInquiryCode("XXX");
        inquiryRequestDO.setStatus("PUBLISHED");
        InquirySupplierDO inquirySupplierDO = new InquirySupplierDO();
        inquirySupplierDO.setCode("S001");
        inquirySupplierDO.setName("供应商1");
        inquirySupplierDO.setAudited("Y");
        inquirySupplierDO.setConfirmed("Y");
        List<InquiryProductDO> productDOList = new ArrayList<>();
        InquiryProductDO productDO1 = new InquiryProductDO();
        productDO1.setCode("P001");
        productDO1.setName("商品1");
        productDO1.setQuantity(BigDecimal.TEN);
        productDO1.setLineNumber(1);
        productDOList.add(productDO1);
        InquiryProductDO productDO2 = new InquiryProductDO();
        productDO2.setCode("P002");
        productDO2.setName("商品2");
        productDO2.setQuantity(BigDecimal.ONE);
        productDO2.setLineNumber(1);
        productDOList.add(productDO2);
        InquirySupplierProducts inquirySupplierProducts = InquiryRequestConverter.INSTANCE.toDto(inquiryRequestDO, inquirySupplierDO, productDOList);
        log.info("{}", inquirySupplierProducts);
        assertNotNull(inquirySupplierProducts);
        assertNotNull(inquirySupplierProducts.getSupplier());
        assertEquals(2, inquirySupplierProducts.getProducts().size());
        assertEquals("PUBLISHED", inquirySupplierProducts.getStatus());
        assertEquals("S001", inquirySupplierProducts.getSupplier().getCode());
        assertEquals("P002", inquirySupplierProducts.getProducts().get(1).getCode());
    }
}
