package com.island.isrm.core.inquiry.port.external;

import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.external.QuoteCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class QuoteCodeServiceRandom implements QuoteCodeService {
    @Override
    public QuoteCode generateSerialNumber() {
        int num = 1000000 + new Random().nextInt(1000000);
        return new QuoteCode("BJ" + num);
    }
}
