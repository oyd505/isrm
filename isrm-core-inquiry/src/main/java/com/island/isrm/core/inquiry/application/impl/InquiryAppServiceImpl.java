package com.island.isrm.core.inquiry.application.impl;

import com.island.isrm.core.inquiry.application.InquiryAppService;
import com.island.isrm.core.inquiry.application.InquiryAssembler;
import com.island.isrm.core.inquiry.application.command.*;
import com.island.isrm.core.inquiry.domain.dp.inquiry.*;
import com.island.isrm.core.inquiry.domain.dp.quote.QuoteCode;
import com.island.isrm.core.inquiry.domain.entity.InquiryProduct;
import com.island.isrm.core.inquiry.domain.entity.InquiryRequest;
import com.island.isrm.core.inquiry.domain.entity.InquirySupplier;
import com.island.isrm.core.inquiry.domain.event.QuoteSubmittedEvent;
import com.island.isrm.core.inquiry.domain.external.InquiryCodeService;
import com.island.isrm.core.inquiry.domain.repo.InquiryRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 询价应用服务实现类，用于处理询价相关的业务逻辑
 *
 * @author dao.ouyang
 * @since 2024-12-22
 */
@Service
public class InquiryAppServiceImpl implements InquiryAppService {
    // 用于将查询请求命令转换为实体对象的组装器
    private final InquiryAssembler inquiryAssembler = InquiryAssembler.instance;
    // 查询请求的存储库，用于执行数据持久化操作
    private final InquiryRequestRepository inquiryRequestRepository;
    // 用于生成查询编码的服务
    private final InquiryCodeService inquiryCodeService;

    /**
     * 构造函数，用于注入依赖项
     *
     * @param inquiryRequestRepository 查询请求的存储库
     * @param inquiryCodeService       用于生成查询编码的服务
     */
    public InquiryAppServiceImpl(InquiryRequestRepository inquiryRequestRepository,
                                 InquiryCodeService inquiryCodeService) {
        this.inquiryRequestRepository = inquiryRequestRepository;
        this.inquiryCodeService = inquiryCodeService;
    }

    /**
     * 创建一个新的询价请求
     *
     * @param command 创建查询请求的命令对象，包含查询请求的基本信息
     * @return 询价编码
     */
    @Transactional
    @Override
    public InquiryCode create(CreateInquiryRequestCmd command) {
        // 生成唯一的查询编码
        InquiryCode inquiryCode = this.inquiryCodeService.generateSerialNumber();
        // 将创建查询请求的命令转换为待添加的实体对象
        InquiryRequest inquiryRequest = this.inquiryAssembler.toAddEntity(command, inquiryCode, InquiryStatus.PENDING);
        // 将新的查询请求实体添加到存储库中并返回其查询编码
        return this.inquiryRequestRepository.add(inquiryRequest);
    }

    /**
     * 更新指定的询价请求
     *
     * @param command 更新查询请求的命令对象，包含新的查询请求的基本信息
     */
    @Transactional
    @Override
    public void update(UpdateInquiryRequestCmd command) {
        // 将更新查询请求的命令转换为待更新的实体对象
        InquiryRequest input = this.inquiryAssembler.toUpdateEntity(command, InquiryStatus.PENDING);
        // 从存储库中查找要更新的查询请求实体
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(input.getInquiryCode());
        // 使用输入的实体数据更新查询请求实体
        inquiryRequest.update(input);
        // 更新存储库中的查询请求实体
        this.inquiryRequestRepository.update(inquiryRequest);
    }


    /**
     * 删除指定的询价请求
     *
     * @param inquiryCode 询价请求的唯一标识码
     */
    @Transactional
    @Override
    public void remove(InquiryCode inquiryCode) {
        // 根据标识码查找询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(inquiryCode);
        // 检查询价请求是否可以被编辑
        inquiryRequest.checkEditable();
        // 从数据库中移除询价请求
        this.inquiryRequestRepository.removeAll(inquiryCode);
    }

    /**
     * 提交询价请求
     *
     * @param inquiryCode 询价请求的唯一标识码
     */
    @Transactional
    @Override
    public void submit(InquiryCode inquiryCode) {
        // 根据标识码查找询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findAll(inquiryCode);
        // 提交询价请求
        inquiryRequest.submit();
        // 更新并查询商品信息
        this.inquiryRequestRepository.updateAndInquiryProduct(inquiryRequest);
    }

    /**
     * 发布询价请求
     *
     * @param inquiryCode 询价请求的唯一标识码
     */
    @Transactional
    @Override
    public void publish(InquiryCode inquiryCode) {
        // 根据标识码查找询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findAll(inquiryCode);
        // 发布询价请求
        inquiryRequest.publish();
        // 更新询价请求到数据库
        this.inquiryRequestRepository.update(inquiryRequest);
    }

    /**
     * 添加商品到询价请求中
     *
     * @param inquiryCode 询价请求的唯一标识码
     * @param command     添加商品的命令对象，包含商品信息
     * @return 新增商品的唯一标识ID
     */
    @Transactional
    @Override
    public Long addProduct(String inquiryCode, AddInquiryProductCmd command) {
        // 将命令对象转换为商品实体
        InquiryProduct inquiryProduct = this.inquiryAssembler.toAddEntity(inquiryCode, command);
        // 根据标识码查找询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.find(inquiryProduct.getInquiryCode());
        // 向询价请求中添加商品
        inquiryRequest.addProduct(inquiryProduct);
        // 向数据库中添加一个询价商品并返回其ID
        return inquiryRequestRepository.addOneInquiryProduct(inquiryRequest).getId();
    }

    /**
     * 更新询价请求中的商品信息
     *
     * @param inquiryCode 询价请求的唯一标识码
     * @param command     更新商品的命令对象，包含新的商品信息
     */
    @Transactional
    @Override
    public void updateProduct(String inquiryCode, UpdateInquiryProductCmd command) {
        // 将命令对象转换为需要更新的商品实体
        InquiryProduct input = this.inquiryAssembler.toUpdateEntity(inquiryCode, command);
        // 查找包含指定商品的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquiryProduct(
                input.getInquiryCode(), input.getInquiryProductId()
        );
        // 更新商品信息
        inquiryRequest.updateProduct(input);
        // 更新数据库中的询价商品信息
        this.inquiryRequestRepository.updateInquiryProduct(inquiryRequest);
    }

    /**
     * 从询价请求中移除商品
     *
     * @param inquiryCode      询价请求的唯一标识码
     * @param inquiryProductId 要移除的商品的唯一标识ID
     */
    @Transactional
    @Override
    public void removeProduct(InquiryCode inquiryCode, InquiryProductId inquiryProductId) {
        // 查找包含指定商品的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquiryProduct(
                inquiryCode, inquiryProductId
        );
        // 检查询价请求是否可以被编辑
        inquiryRequest.checkEditable();
        // 从数据库中移除指定的商品
        this.inquiryRequestRepository.removeOneInquiryProduct(inquiryProductId);
    }

    /**
     * 邀请供应商参与询价
     *
     * @param inquiryCode 询价请求的唯一标识码
     * @param command     添加供应商的命令对象，包含供应商信息
     * @return 新增供应商的唯一标识ID
     */
    @Transactional
    @Override
    public Long inviteSupplier(String inquiryCode, AddInquirySupplierCmd command) {
        // 将命令对象转换为供应商实体
        InquirySupplier inquirySupplier = this.inquiryAssembler.toAddEntity(inquiryCode, command);
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquirySupplier.getInquiryCode(), inquirySupplier.getSupplier().getCode());
        // 邀请供应商参与询价
        inquiryRequest.inviteSupplier(inquirySupplier);
        // 向数据库中添加一个询价供应商并返回其ID
        return this.inquiryRequestRepository.addOneInquirySupplier(inquiryRequest).getId();
    }

    /**
     * 申请成为询价请求的供应商
     *
     * @param inquiryCode 询价请求的唯一标识码
     * @param command     供应商申请的命令对象，包含申请信息
     * @return 询价供应商ID
     */
    @Transactional
    @Override
    public Long applySupplier(String inquiryCode, ApplyInquirySupplierCmd command) {
        // 将命令对象转换为供应商实体
        InquirySupplier inquirySupplier = this.inquiryAssembler.toApplyEntity(inquiryCode, command);
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquirySupplier.getInquiryCode(), inquirySupplier.getSupplier().getCode()
        );
        // 申请成为询价请求的供应商
        inquiryRequest.applySupplier(inquirySupplier);
        // 向数据库中添加一个询价供应商申请并返回其ID
        return this.inquiryRequestRepository.addOneInquirySupplier(inquiryRequest).getId();
    }

    /**
     * 审核供应商的参与资格
     *
     * @param inquiryCode       询价请求的唯一标识码
     * @param inquirySupplierId 询价供应商ID
     */
    @Transactional
    @Override
    public void auditSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId) {
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquiryCode, inquirySupplierId
        );
        // 审核供应商申请
        inquiryRequest.auditSupplier(inquirySupplierId);
        // 更新数据库中的询价供应商信息
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    /**
     * 确认供应商的邀请
     *
     * @param inquiryCode  询价请求的唯一标识码
     * @param supplierCode 供应商的唯一标识码
     */
    @Transactional
    @Override
    public void confirmInvitation(InquiryCode inquiryCode, SupplierCode supplierCode) {
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(inquiryCode, supplierCode);
        // 确认对供应商的邀请
        inquiryRequest.confirmInvitation(supplierCode);
        // 更新数据库中的询价供应商信息
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    /**
     * 更新询价请求中的供应商信息
     *
     * @param inquiryCode 询价请求的唯一标识码
     * @param command     更新供应商的命令对象，包含新的供应商信息
     */
    @Transactional
    @Override
    public void updateSupplier(String inquiryCode, UpdateInquirySupplierCmd command) {
        // 将命令对象转换为需要更新的供应商实体
        InquirySupplier input = this.inquiryAssembler.toUpdateEntity(inquiryCode, command);
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                input.getInquiryCode(), input.getInquirySupplierId()
        );
        // 更新供应商信息
        inquiryRequest.updateSupplier(input);
        // 更新数据库中的询价供应商信息
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }

    /**
     * 从询价请求中移除供应商
     *
     * @param inquiryCode       询价请求的唯一标识码
     * @param inquirySupplierId 要移除的供应商的唯一标识ID
     */
    @Transactional
    @Override
    public void removeSupplier(InquiryCode inquiryCode, InquirySupplierId inquirySupplierId) {
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(
                inquiryCode, inquirySupplierId
        );
        // 检查询价请求是否可以被编辑
        inquiryRequest.checkEditable();
        // 从数据库中移除指定的供应商
        this.inquiryRequestRepository.removeOneInquirySupplier(inquirySupplierId);
    }

    /**
     * 更新供应商的报价进度
     *
     * @param event 供应商提交报价的事件对象，包含报价信息
     */
    @Transactional
    @Override
    public void updateQuoteProgress(QuoteSubmittedEvent event) {
        // 创建供应商和询价请求的唯一标识码对象
        SupplierCode supplierCode = new SupplierCode(event.getSupplierCode());
        InquiryCode inquiryCode = new InquiryCode(event.getInquiryCode());
        // 查找与指定供应商相关的询价请求
        InquiryRequest inquiryRequest = this.inquiryRequestRepository.findOneInquirySupplier(inquiryCode, supplierCode);
        // 更新供应商的报价进度
        inquiryRequest.updateQuoteProgress(supplierCode, new QuoteCode(event.getQuoteCode()));
        // 更新数据库中的询价供应商信息
        this.inquiryRequestRepository.updateInquirySupplier(inquiryRequest);
    }
}
