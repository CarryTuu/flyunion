<template>
	<!-- 侧边栏菜单 -->
	<el-menu class="el-menu" mode="vertical">
		<!-- 遍历菜单项，生成带有图标的菜单 -->
		<el-tooltip
				v-for="item in menuItems"
				:key="item.index"
				class="box-item"
				effect="dark"
				:content="item.content"
				placement="right"
		>
			<el-menu-item :index="item.index" @click="navigateTo(item.path)">
				<el-icon class="icon-container">
					<box-icon
							color="#657792"
							:type="item.icon.includes('-') ? 'solid' : ''"
							:name="item.icon"
					></box-icon>
				</el-icon>
			</el-menu-item>
		</el-tooltip>
	</el-menu>
</template>

<script>
import va from "@/utils/va.js";

/**
 * 侧边栏组件定义
 * 该组件包含了一个垂直菜单，用于导航和展示菜单项
 */
export default {
	
	// 组件数据
	data() {
		return {
			// 当前激活的菜单项索引
			// 菜单项列表，包含各个菜单项的索引、路径、图标和内容
			menuItems: [
				{ index: '1', path: '/ADM/', icon: 'home', content: '主页'},
				{ index: '2', path: '/ADM/Pilot', icon: 'user', content: '飞行员信息编辑'},
				{ index: '3', path: '/ADM/FlightPlanList', icon: 'chart', content: '可执行航线编辑'},
				{ index: '4', path: '/ADM/PlaneList', icon: 'plane-alt', content: '飞机信息编辑'},
				{ index: '5', path: '/ADM/FlightLogList', icon: 'plane-land', content: '需审批航班信息'},
				{ index: '6', path: '/ADM/Constants', icon: 'cog', content: '系统常量信息'},
			]
		};
	},
	
	// 组件方法
	methods: {
		/**
		 * 处理菜单项选择事件
		 * @param {string} index - 被选中菜单项的索引
		 */
		
		/**
		 * 导航到指定路径
		 * @param {string} path - 目标路径
		 */
		navigateTo(path) {
			if(path === "/logOut"){
				va.put("/user/logOut")
						.then(() => {
							localStorage.removeItem("loginUser")
							this.$message.success("退出登录成功！")
							this.$router.push("/")
						})
			}else {
				this.$router.push(path);
			}
		},
		
		/**
		 * 退出登录，导航到登录页面
		 */
	}
	
};
</script>



<style scoped>
/* 侧边栏菜单样式 */
.el-menu {
	height: 100vh;
	width: 60px;
	background-color: rgb(244, 243, 243);
	padding-top: 10px;
	display: flex;
	flex-direction: column;
}

/* 底部元素样式，使其自动推到底部 */
.bottom {
	margin-top: auto;
	margin-bottom: 50px;
}

/* 菜单项样式 */
.el-menu-item {
	display: flex;
	justify-content: center;
	margin: 10px 12px;
}

/* 图标容器样式 */
.icon-container {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 40px;
	height: 40px;
	border-radius: 8px;
	transition: background-color 0.3s ease;
}

/* 激活状态的菜单项样式 */
.active-menu .icon-container {
	background-color: #1f2b3b;
	color: white;
}
</style>