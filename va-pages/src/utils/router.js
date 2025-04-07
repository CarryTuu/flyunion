import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import Support from "@/components/Support.vue";
import PasswordReset from "@/components/PasswordReset.vue";
import Dashboard from "@/components/Dashboard.vue";

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
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router