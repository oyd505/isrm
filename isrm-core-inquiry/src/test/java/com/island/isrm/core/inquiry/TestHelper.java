package com.island.isrm.core.inquiry;

import com.island.isrm.core.common.domain.dp.Reason;
import com.island.isrm.core.common.domain.dp.Title;
import com.island.isrm.core.inquiry.domain.dp.inquiry.Buyer;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryStatus;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;

public class TestHelper {
    public static InquiryRequest createInquiryRequest() {
        InquiryRequest inquiryRequest = new InquiryRequest(new InquiryCode("XJ-00001"), InquiryStatus.PENDING);
        inquiryRequest.setTitle(new Title("询价001"))
                .setBuyer(new Buyer("007", "李连杰"))
                .setReason(new Reason("询价测试"));
        return inquiryRequest;
    }

    public static InquiryRequest createInquiryRequest(InquiryStatus inquiryStatus) {
        InquiryRequest inquiryRequest = new InquiryRequest(new InquiryCode("XJ-00001"), inquiryStatus);
        inquiryRequest.setTitle(new Title("询价001"))
                .setBuyer(new Buyer("007", "李连杰"))
                .setReason(new Reason("询价测试"));
        return inquiryRequest;
    }
}
