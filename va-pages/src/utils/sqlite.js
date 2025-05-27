import axios from "axios";

const sqliteService = axios.create({

    baseURL: "http://localhost:9998",
    withCredentials: true
});
//请求拦截器
sqliteService.interceptors.request.use(config => {
    return config;
}, error => {
    return Promise.reject(error);
});

//响应拦截器
sqliteService.interceptors.response.use(resp => {
    return resp.data;
}, error => {
    return Promise.reject(error);
});
export default sqliteService;