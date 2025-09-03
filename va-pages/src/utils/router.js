import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import Support from "@/components/Support.vue";
import PasswordReset from "@/components/PasswordReset.vue";
import Dashboard from "@/components/Dashboard.vue";
import CompanyList from "@/components/company/CompanyList.vue";
import CompanyDetail from "@/components/company/CompanyDetail.vue";
import PlaneList from "@/components/Plane/PlaneList.vue";
import PlaneDetail from "@/components/Plane/PlaneDetail.vue";
import NewFlight from "@/components/newFlight/newFlight.vue";

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
    },
    {
        path: "/NewFlight",
        component: NewFlight
    },
    {
        path: "/PlaneList",
        component: PlaneList
    },
    {
        path: "/PlaneDetail/:code",
        component: PlaneDetail,
        name: "PlaneDetail"
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router