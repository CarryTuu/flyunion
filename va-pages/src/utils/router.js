import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import Support from "@/components/Support.vue";
import PasswordReset from "@/components/PasswordReset.vue";
import Dashboard from "@/components/Dashboard.vue";
import CompanyList from "@/components/company/CompanyList.vue";
import CompanyDetail from "@/components/company/CompanyDetail.vue";

const routes = [
    {
        path: "/",
        component: Login
    },
    {
        path: "/Register",
        component: Register
    },
    {
        path: "/Support",
        component: Support
    },
    {
        path: "/PasswordReset",
        component: PasswordReset
    },
    {
        path: "/Dashboard",
        component: Dashboard
    },
    {
        path: "/Company",
        component: CompanyList
    },
    {
        path: "/Company/:id",
        name: "CompanyDetail",
        component: CompanyDetail
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router