package com.island.isrm.core.inquiry.port.external;

import com.island.isrm.core.inquiry.domain.dp.inquiry.InquiryCode;
import com.island.isrm.core.inquiry.domain.external.InquiryCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 实现 InquiryCodeService 接口的类，用于生成随机的询问编码
 * 该类使用 Random 类生成一个随机数，并将其格式化为询问编码的形式
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class InquiryCodeServiceRandom implements InquiryCodeService {
    /**
     * 生成一个随机的询问编码
     *
     * @return InquiryCode 实例，包含一个以 "XJ" 开头后跟七位随机数字的编码
     */
    @Override
    public InquiryCode generateSerialNumber() {
        // 生成一个1百万到2百万之间的随机整数，确保编码的唯一性和随机性
        int num = 1000000 + new Random().nextInt(1000000);
        // 将随机整数与前缀 "XJ" 拼接，创建并返回 InquiryCode 对象
        return new InquiryCode("XJ" + num);
    }
}
