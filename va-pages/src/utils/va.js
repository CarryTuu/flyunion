import axios from "axios";
import showUnauthorizedAlert from "@/utils/Unauthorized.js";

const service = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL
});
//请求拦截器
service.interceptors.request.use(config => {
    //无需拦截的url
    const noAuthPaths = ['/user/changePassword', '/user/register', '/user/login/email', '/user/login/cid'];
    if (!noAuthPaths.some(path => config.url.includes(path))) {
        const token = localStorage.getItem('loginUser');
        if (token) {
            config.headers.Authorization = token;
        }
    }
    return config;
}, error => {
    return Promise.reject(error);
});

//响应拦截器
service.interceptors.response.use(resp => {
    return resp.data;
}, error => {
    if(error.status === 401){
        showUnauthorizedAlert()
        setTimeout(() => {
            window.location.href = "/"
        }, 3000)
    }
    return Promise.reject(error);
});
export default service;