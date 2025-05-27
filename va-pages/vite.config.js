import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    devServer: {
        proxy: {
            '/api': {
                target: `http://localhost:9999`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            },
            '/api2': {
                target: `http://localhost:9998`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api2': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            }
        }
    }

})
