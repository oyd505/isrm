package com.island.isrm.core.inquiry.port.external;

import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.external.QuoteCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 实现QuoteCodeService接口，提供生成报价单号的功能
 * 本实现类使用随机数生成序列号，适用于需要唯一标识报价单的场景
 */
@Service
public class QuoteCodeServiceRandom implements QuoteCodeService {
    /**
     * 生成一个唯一的报价单号
     *
     * @return 返回一个包含随机生成的北京地区报价单号的QuoteCode实例
     */
    @Override
    public QuoteCode generateSerialNumber() {
        // 生成一个1000000到1999999之间的随机数，确保单号的唯一性
        int num = 1000000 + new Random().nextInt(1000000);
        // 将随机数与"BJ"前缀拼接，形成北京地区的报价单号
        return new QuoteCode("BJ" + num);
    }
}
