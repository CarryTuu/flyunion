import axios from "axios";

const service = axios.create({

    baseURL: "http://localhost:9999",
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:5174',
        'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Content-Type'
    }
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
    return Promise.reject(error);
});
export default service;