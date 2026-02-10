import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
    ],
    base: '/',
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    devServer: {
        proxy: {
            '/api': {
                target: `https://api.va.tysong.cn`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            },
            '/api2': {
                target: `https://api.sqlite.tysong.cn`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api2': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            }
        }
    },
    server: {
        proxy: {
            '/api': {
                target: `https://api.va.tysong.cn`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            },
            '/api2': {
                target: `https://api.sqlite.tysong.cn`, // 替换为你的后端URL
                changeOrigin: true, // 开启代理服务器
                pathRewrite: {
                    '^/api2': '' // 将所有以/api开头的请求路径重写为空字符串
                }
            }
        }
    },
    build: {
        // 构建输出目录
        outDir: 'dist',

        // 生成的静态资源路径
        assetsDir: 'assets',

        // 设置 sourcemap
        sourcemap: false,

        // 分块策略
        rollupOptions: {
            output: {
                chunkFileNames: 'assets/js/[name]-[hash].js',
                entryFileNames: 'assets/js/[name]-[hash].js',
                assetFileNames: 'assets/[ext]/[name]-[hash].[ext]'
            }
        }
    }
})
