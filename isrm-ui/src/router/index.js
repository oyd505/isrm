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
