import service from "./request";

export const login = (username, password) =>
    service.post("/login", null, {
        headers: {
            Authorization: 'Basic ' + btoa(username + ':' + password)
        }
    });

// ------ 用户 ------
// 获取用户列表
export const getUserList = (pageNumber, pageSize) =>
    service.get("/v1/user/page/basic?pageNumber=".concat(pageNumber - 1).concat("&pageSize=").concat(pageSize));
// 获取用户详情
export const getUser = (userName) =>
    service.get("/v1/user/".concat(userName));
// 创建用户
export const saveUser = (user) =>
    service.post("/v1/user/create", user);
// 更新用户信息
export const updateUser = (user) =>
    service.post("/v1/user/update", user);
// 设置角色
export const updateRoles = (userName, roles) =>
    service.post("/v1/user/update/".concat(userName).concat("/roles"), roles);
// 禁用用户
export const disableUser = (userName) =>
    service.post("/v1/user/disable/".concat(userName));
// 启用用户
export const enableUser = (userName) =>
    service.post("/v1/user/enable/".concat(userName));
// 删除用户
export const delUser = (userName) =>
    service.post("/v1/user/remove/".concat(userName));


// ------ 员工 ------
// 获取员工列表
export const getEmployeeList = (pageNumber, pageSize) =>
    service.get("/v1/employee/page/basic?pageNumber=".concat(pageNumber - 1).concat("&pageSize=").concat(pageSize));
// 获取精简员工列表
export const getThinEmployeeList = () =>
    service.get("/v1/employee/list/code/name");
// 获取员工详情
export const getEmployee = (employeeCode) =>
    service.get("/v1/employee/".concat(employeeCode));
// 创建员工
export const saveEmployee = (employee) =>
    service.post("/v1/employee/create", employee);
// 更新员工信息
export const updateEmployee = (employee) =>
    service.post("/v1/employee/update", employee);
// 删除员工
export const delEmployee = (employeeCode) =>
    service.post("/v1/employee/remove/".concat(employeeCode));

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
// 获取商品列表
export const getProductList = (pageNumber, pageSize) =>
    service.get("/v1/product/page/basic?pageNumber=".concat(pageNumber - 1).concat("&pageSize=").concat(pageSize));
// 获取精简商品列表
export const getThinProductList = () =>
    service.get("/v1/product/list/code/name");
// 获取商品详情
export const getProduct = (productCode) =>
    service.get("/v1/product/".concat(productCode));
// 创建商品
export const saveProduct = (product) =>
    service.post("/v1/product/create", product);
// 更新商品信息
export const updateProduct = (product) =>
    service.post("/v1/product/update", product);
// 删除商品
export const delProduct = (productCode) =>
    service.post("/v1/product/remove/".concat(productCode));

// ------ 供应商 ------
// 获取供应商列表
export const getSupplierList = (pageNumber, pageSize) =>
    service.get("/v1/supplier/page/basic?pageNumber=".concat(pageNumber - 1).concat("&pageSize=").concat(pageSize));
// 获取精简供应商列表
export const getThinSupplierList = () =>
    service.get("/v1/supplier/list/code/name");
// 获取供应商详情
export const getSupplier = (supplierCode) =>
    service.get("/v1/supplier/".concat(supplierCode));
// 创建供应商
export const saveSupplier = (supplier) =>
    service.post("/v1/supplier/create", supplier);
// 更新供应商信息
export const updateSupplier = (supplier) =>
    service.post("/v1/supplier/update", supplier);
// 删除供应商
export const delSupplier = (supplierCode) =>
    service.post("/v1/supplier/remove/".concat(supplierCode));

// ------ 供应商联系人 ------
// 获取供应商联系人列表
export const getSupplierContactList = (supplierCode) =>
    service.get("/v1/supplier/".concat(supplierCode).concat("/contact/list"));
// 获取供应商联系人列表
export const getThinContactList = (supplierCode) =>
    service.get("/v1/supplier/".concat(supplierCode).concat("/contact/list/name/phone"));
// 获取供应商联系人详情
export const getSupplierContact = (supplierCode, supplierContactId) =>
    service.get(
        "/v1/supplier/".concat(supplierCode).concat("/contact/").concat(supplierContactId)
    );
// 新增供应商联系人
export const saveSupplierContact = (supplierCode, product) =>
    service.post(
        "/v1/supplier/".concat(supplierCode).concat("/contact/add"),
        product
    );
// 更新供应商联系人信息
export const updateSupplierContact = (supplierCode, product) =>
    service.post(
        "/v1/supplier/".concat(supplierCode).concat("/contact/update"),
        product
    );
// 删除供应商联系人
export const delSupplierContact = (supplierCode, supplierContactId) =>
    service.post(
        "/v1/supplier/"
            .concat(supplierCode)
            .concat("/contact/remove/")
            .concat(supplierContactId)
    );

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