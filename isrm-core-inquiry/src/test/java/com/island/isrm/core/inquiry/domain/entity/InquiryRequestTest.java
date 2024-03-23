package com.island.isrm.core.inquiry.domain.entity;

import com.island.isrm.core.common.domain.dp.Currency;
import com.island.isrm.core.common.domain.dp.Reason;
import com.island.isrm.core.common.domain.dp.Title;
import com.island.isrm.core.common.exception.BizException;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@Slf4j
public class InquiryRequestTest {

    @Test
    public void testUpdateInquiryRequest() {
        // Given
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryStatus inquiryStatus = InquiryStatus.PENDING;
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        InquiryRequest newInquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        newInquiryRequest.setTitle(new Title("New Title"));
        newInquiryRequest.setParticipation(Participation.OPEN);
        newInquiryRequest.setBuyer(new Buyer("John", "John"));
        newInquiryRequest.setReason(new Reason("New reason"));
        newInquiryRequest.setQuoteTime(new QuoteTime(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1)));
        newInquiryRequest.setPaymentTerms(new PaymentTerms("Net 30"));
        newInquiryRequest.setCurrency(new Currency("USD"));
        newInquiryRequest.setPurchasingOrg(new PurchasingOrg("Org1", "Org1"));

        // When
        inquiryRequest.update(newInquiryRequest);

        // Then
        assertEquals(newInquiryRequest.getTitle(), inquiryRequest.getTitle());
        assertEquals(newInquiryRequest.getParticipation(), inquiryRequest.getParticipation());
        assertEquals(newInquiryRequest.getBuyer(), inquiryRequest.getBuyer());
        assertEquals(newInquiryRequest.getReason(), inquiryRequest.getReason());
        assertEquals(newInquiryRequest.getQuoteTime(), inquiryRequest.getQuoteTime());
        assertEquals(newInquiryRequest.getPaymentTerms(), inquiryRequest.getPaymentTerms());
        assertEquals(newInquiryRequest.getCurrency(), inquiryRequest.getCurrency());
        assertEquals(newInquiryRequest.getPurchasingOrg(), inquiryRequest.getPurchasingOrg());
    }

    @Test
    public void testSubmitInquiryRequest() {
        // Given
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryStatus inquiryStatus = InquiryStatus.PENDING;
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        inquiryRequest.setParticipation(Participation.OPEN);
        inquiryRequest.addProduct(new InquiryProduct(new InquiryProductId(1L), new InquiryCode("Product1")));
        inquiryRequest.setQuoteTime(new QuoteTime(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1)));

        // When
        inquiryRequest.submit();

        // Then
        assertEquals(InquiryStatus.SUBMITTED, inquiryRequest.getInquiryStatus());
    }

    @Test
    public void testCheckQuoteEnabledInquiryRequest() {
        // Given
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryStatus inquiryStatus = InquiryStatus.SUBMITTED;
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        SupplierCode supplierCode = new SupplierCode("123");

        // When/Then
        assertThrows(BizException.class, () -> inquiryRequest.checkQuoteEnabled(supplierCode));
    }

    @Test
    public void inviteSupplier_AddsSupplierToEmptyList() {
        // Arrange
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryStatus inquiryStatus = InquiryStatus.PENDING;
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        inquiryRequest.setParticipation(Participation.INVITED);
        InquirySupplier supplier = new InquirySupplier(new InquirySupplierId(1L), inquiryCode);

        // Act
        inquiryRequest.inviteSupplier(supplier);

        // Assert
        assertTrue(inquiryRequest.getInquirySuppliers().contains(supplier));
    }

    @Test
    public void testApplySupplier_HappyPath() {
        // Arrange
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, InquiryStatus.PUBLISHED);
        inquiryRequest.setParticipation(Participation.OPEN);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endTime = currentTime.plusHours(1);
        inquiryRequest.setQuoteTime(new QuoteTime(currentTime, endTime));
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(1L), inquiryCode);

        // Act
        inquiryRequest.applySupplier(inquirySupplier);

        // Assert
        assertTrue(inquiryRequest.getInquirySuppliers().contains(inquirySupplier));
        assertTrue(inquiryRequest.getInquirySuppliers().stream().allMatch(iSupplier -> iSupplier.getConfirmed().isOk()));
    }

    @Test
    public void testApplySupplier_NotOpenParticipation() {
        // Arrange
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryStatus inquiryStatus = InquiryStatus.PUBLISHED;
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, inquiryStatus);
        inquiryRequest.setParticipation(Participation.INVITED);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endTime = currentTime.plusHours(1);
        inquiryRequest.setQuoteTime(new QuoteTime(currentTime, endTime));
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(1L), inquiryCode);

        // Act & Assert
        BizException exception = assertThrows(BizException.class, () -> inquiryRequest.applySupplier(inquirySupplier));
        assertEquals("OPEN 参与方式的询价才允许供应商申请参加", exception.getMessage());
    }

    @Test
    public void testApplySupplier_NotPublishedStatus() {
        // Arrange
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, InquiryStatus.PENDING);
        inquiryRequest.setParticipation(Participation.OPEN);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime endTime = currentTime.plusHours(1);
        inquiryRequest.setQuoteTime(new QuoteTime(currentTime, endTime));
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(1L), inquiryCode);

        // Act & Assert
        BizException exception = assertThrows(BizException.class, () -> inquiryRequest.applySupplier(inquirySupplier));
        assertEquals("PUBLISHED 状态的询价才允许供应商申请参加", exception.getMessage());
    }

    @Test
    public void testApplySupplier_PastQuoteTime() {
        InquiryCode inquiryCode = new InquiryCode("123");
        InquiryRequest inquiryRequest = new InquiryRequest(inquiryCode, InquiryStatus.PUBLISHED);
        inquiryRequest.setParticipation(Participation.OPEN);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startTime = currentTime.minusHours(10);
        inquiryRequest.setQuoteTime(new QuoteTime(startTime, currentTime.minusHours(1)));
        InquirySupplier inquirySupplier = new InquirySupplier(new InquirySupplierId(1L), inquiryCode);

        BizException exception = assertThrows(BizException.class, () -> inquiryRequest.applySupplier(inquirySupplier));
        assertEquals("报价时间已过，无法申请参加", exception.getMessage());
    }
}
