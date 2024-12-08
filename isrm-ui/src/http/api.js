import service from "./request";

export const login = (username, password) =>
    service.post("/login", null, {
        headers: {
            Authorization: 'Basic ' + btoa(username + ':' + password)
        }
    });

// ------ 员工 ------
// 获取精简员工列表
export const getThinEmployeeList = () =>
    service.get("/v1/employee/list/code/name");

// ------ 组织 ------
// 获取精简采购组织列表
export const getThinPurOrganizationList = () =>
    service.get("/v1/organization/list/po/code/name");
// 获取精简公司组织列表
export const getThinComOrganizationList = () =>
    service.get("/v1/organization/list/co/code/name");
// 获取精简工厂组织列表
export const getThinFtyOrganizationList = () =>
    service.get("/v1/organization/list/fo/code/name");

// ------ 商品 ------
// 获取精简商品列表
export const getThinProductList = () =>
    service.get("/v1/product/list/code/name");

// ------ 供应商 ------
// 获取精简供应商列表
export const getThinSupplierList = () =>
    service.get("/v1/supplier/list/code/name");
// 获取供应商联系人列表
export const getThinContactList = (supplierCode) =>
    service.get("/v1/supplier/".concat(supplierCode).concat("/contact/list/name/phone"));

// ------ 询价请求 ------
// 获取询价请求列表
export const getInquiryRequestList = (pageNumber, pageSize) =>
    service.get("/v1/inquiry/request/list?pageNumber=".concat(pageNumber - 1).concat("&pageSize=").concat(pageSize));
// 获取询价请求列表-报价
export const getInquiryRequestListForQuote = (supplierCode) =>
    service.get("/v1/inquiry/request/list/quote?supplierCode=".concat(supplierCode));
// 获取询价请求详情
export const getInquiryRequest = (inquiryCode) =>
    service.get("/v1/inquiry/request/".concat(inquiryCode));
// 创建询价请求
export const saveInquiryRequest = (inquiry) =>
    service.post("/v1/inquiry/request/save", inquiry);
// 更新询价请求信息
export const updateInquiryRequest = (inquiry) =>
    service.post("/v1/inquiry/request/update", inquiry);
// 删除询价请求
export const delInquiryRequest = (inquiryCode) =>
    service.post("/v1/inquiry/request/delete/".concat(inquiryCode));
// 提交询价请求
export const submitInquiryRequest = (inquiryCode) =>
    service.post("/v1/inquiry/request/submit/".concat(inquiryCode));
// 发布询价请求
export const publishInquiryRequest = (inquiryCode) =>
    service.post("/v1/inquiry/request/publish/".concat(inquiryCode));

// ------ 询价商品 ------
// 获取询价商品列表
export const getInquiryProductList = (inquiryCode) =>
    service.get("/v1/inquiry/".concat(inquiryCode).concat("/product/list"));
// 获取询价商品详情
export const getInquiryProduct = (inquiryCode, inquiryProductId) =>
    service.get(
        "/v1/inquiry/".concat(inquiryCode).concat("/product/").concat(inquiryProductId)
    );
// 新增询价商品
export const saveInquiryProduct = (inquiryCode, product) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/product/save"),
        product
    );
// 更新询价商品信息
export const updateInquiryProduct = (inquiryCode, product) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/product/update"),
        product
    );
// 删除询价商品
export const delInquiryProduct = (inquiryCode, inquiryProductId) =>
    service.post(
        "/v1/inquiry/"
            .concat(inquiryCode)
            .concat("/product/delete/")
            .concat(inquiryProductId)
    );

// ------ 询价供应商 ------
// 获取询价供应商列表
export const getInquirySupplierList = (inquiryCode) =>
    service.get("/v1/inquiry/".concat(inquiryCode).concat("/supplier/list"));
// 获取询价供应商报价列表
export const getInquirySupplierQuoteList = (inquiryCode) =>
    service.get("/v1/inquiry/".concat(inquiryCode).concat("/supplier/quote/list"));
// 获取询价供应商品详情
export const getInquirySupplier = (inquiryCode, inquirySupplierId) =>
    service.get(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/").concat(inquirySupplierId)
    );
// 获取询价供应商品详情
export const getInquirySupplierByCode = (inquiryCode, supplierCode) =>
    service.get(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/detail?supplierCode=").concat(supplierCode)
    );
// 邀请询价供应商
export const inviteInquirySupplier = (inquiryCode, supplier) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/save"),
        supplier
    );
// 申请询价供应商
export const applyInquirySupplier = (inquiryCode, supplier) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/apply"),
        supplier
    );
// 确认询价邀请
export const confirmInquiryInvitation = (inquiryCode, supplierCode) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/confirm?supplierCode=").concat(supplierCode)
    );
// 审核询价供应商
export const auditInquirySupplier = (inquiryCode, inquirySupplierId) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/audit/").concat(inquirySupplierId)
    );
// 更新询价供应商信息
export const updateInquirySupplier = (inquiryCode, supplier) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/update"),
        supplier
    );
// 删除询价供应商
export const delInquirySupplier = (inquiryCode, inquirySupplierId) =>
    service.post(
        "/v1/inquiry/".concat(inquiryCode).concat("/supplier/delete/").concat(inquirySupplierId)
    );

// ------ 报价请求 ------
// 获取报价请求列表
export const getQuoteRequestList = (inquiryCode, supplierCode) =>
    service.get("/v1/quote/request/list?inquiryCode=".concat(inquiryCode).concat("&supplierCode=").concat(supplierCode));
// 获取报价请求详情
export const getQuoteRequest = (quoteCode) =>
    service.get("/v1/quote/request/".concat(quoteCode));
// 创建报价
export const createQuoteByInquiry = (inquiryCode, supplierCode) =>
    service.post(
        "/v1/quote/request/save?inquiryCode=".concat(inquiryCode).concat("&supplierCode=").concat(supplierCode)
    );
// 更新报价请求信息
export const updateQuoteRequest = (quote) =>
    service.post("/v1/quote/request/update", quote);
// 删除报价请求
export const delQuoteRequest = (quoteCode) =>
    service.post("/v1/quote/request/delete/".concat(quoteCode));
// 提交报价请求
export const submitQuoteRequest = (quoteCode) =>
    service.post("/v1/quote/request/submit/".concat(quoteCode));

// ------ 报价明细项 ------
// 获取报价明细项列表
export const getQuoteItemList = (quoteCode) =>
    service.get("/v1/quote/".concat(quoteCode).concat("/item/list"));
// 获取报价明细项详情
export const getQuoteItem = (quoteCode, quoteItemId) =>
    service.get(
        "/v1/quote/".concat(quoteCode).concat("/item/").concat(quoteItemId)
    );
// 更新报价明细项信息
export const updateQuoteItem = (quoteCode, item) =>
    service.post(
        "/v1/quote/".concat(quoteCode).concat("/item/update"),
        item
    );