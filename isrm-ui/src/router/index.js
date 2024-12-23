import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {path: "/", name: "login", component: () => import("@/views/LoginView.vue")},
        {
            path: "/main",
            name: "main",
            component: () => import("@/views/MainView.vue"),
            children: [
                {path: "home", component: () => import("@/views/HomeView.vue")},
                // ------ 用户 ------
                {
                    path: "user/list",
                    name: "user",
                    component: () => import("@/views/user/ListView.vue"),
                },
                {
                    path: "user/edit/:userName",
                    component: () => import("@/views/user/EditView.vue"),
                },

                // ------ 员工 ------
                {
                    path: "employee/list",
                    name: "employee",
                    component: () => import("@/views/employee/ListView.vue"),
                },
                {
                    path: "employee/edit/:employeeCode",
                    component: () => import("@/views/employee/EditView.vue"),
                },

                // ------ 供应商 ------
                {
                    path: "supplier/list",
                    name: "supplier",
                    component: () => import("@/views/supplier/ListView.vue"),
                },
                {
                    path: "supplier/edit/:supplierCode",
                    component: () => import("@/views/supplier/EditView.vue"),
                },
                {
                    path: "supplier/contact/list/:supplierCode",
                    component: () => import("@/views/supplier/contact/ListEditView.vue"),
                },
                // ------ 询价 ------
                {
                    path: "inquiry/request/list",
                    name: "inquiry",
                    component: () => import("@/views/inquiry/request/ListView.vue"),
                },
                {
                    path: "inquiry/request/edit/:inquiryCode",
                    component: () => import("@/views/inquiry/request/EditView.vue"),
                },
                {
                    path: "inquiry/product/list/:inquiryCode",
                    component: () => import("@/views/inquiry/product/ListEditView.vue"),
                },
                {
                    path: "inquiry/supplier/list/:inquiryCode",
                    component: () => import("@/views/inquiry/supplier/ListEditView.vue"),
                },
                // ------ 报价 ------
                {
                    path: "inquiry/quote/list/inquiry",
                    component: () => import("@/views/inquiry/quote/InquiryListView.vue"),
                },
                {
                    path: "inquiry/quote/request/list/:inquiryCode",
                    component: () => import("@/views/inquiry/quote/InquiryQuoteView.vue"),
                },
                {
                    path: "inquiry/quote/request/edit/:quoteCode",
                    component: () => import("@/views/inquiry/quote/EditView.vue")
                }
            ]
        }
    ],
});

export default router;
