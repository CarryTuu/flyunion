import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import Support from "@/components/Support.vue";
import PasswordReset from "@/components/PasswordReset.vue";
import Dashboard from "@/components/Dashboard.vue";
import CompanyList from "@/components/company/CompanyList.vue";
import CompanyDetail from "@/components/company/CompanyDetail.vue";
import PlaneList from "@/components/plane/PlaneList.vue";
import PlaneDetail from "@/components/plane/PlaneDetail.vue";
import NewFlight from "@/components/newFlight/NewFlight.vue";
import ChangeInfo from "@/components/userInfo/ChangeInfo.vue";
import ADM from "@/components/admin/Dashboard.vue";
import UserList from "@/components/admin/userInfo/UserList.vue";
import FlightPlanList from "@/components/admin/flightPlan/FlightPlanList.vue";
import FlightPlanDetail from "@/components/admin/flightPlan/FlightPlanDeteil.vue";
import NewFlightPlan from "@/components/admin/flightPlan/NewFlightPlan.vue";
import ADMPlaneList from "@/components/admin/plane/PlaneList.vue"
import NewPlane from "@/components/admin/plane/NewPlane.vue";
import FlightLogList from "@/components/admin/flightLog/FlightLogList.vue";
import FlightLogDetail from "@/components/admin/flightLog/FlightLogDetail.vue";
import ConstantsEdit from "@/components/admin/constants/ConstantsEdit.vue";

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
    },
    {
        path: "/ChangeInfo",
        component: ChangeInfo
    },
    {
        path: "/ADM/",
        component: ADM
    },
    {
        path: "/ADM/Pilot",
        component: UserList
    },
    {
        path: "/ADM/FlightPlanList",
        component: FlightPlanList
    },
    {
        path: "/ADM/FlightPlanDetail/:id",
        component: FlightPlanDetail,
        name: "FlightPlanDetail"
    },
    {
        path: "/ADM/NewFlightPlan",
        component: NewFlightPlan
    },
    {
        path: "/ADM/PlaneList",
        component: ADMPlaneList
    },
    {
        path: "/ADM/NewPlane",
        component: NewPlane
    },
    {
        path: "/ADM/FlightLogList",
        component: FlightLogList
    },
    {
        path: "/ADM/FlightLogDetail/:id",
        component: FlightLogDetail,
        name: "FlightLogDetail"
    },
    {
        path: "/ADM/Constants",
        component: ConstantsEdit
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router