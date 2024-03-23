package com.island.isrm.core.inquiry.port.external;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.external.InquiryCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InquiryCodeServiceRandom implements InquiryCodeService {
    @Override
    public InquiryCode generateSerialNumber() {
        int num = 1000000 + new Random().nextInt(1000000);
        return new InquiryCode("XJ" + num);
    }
}
