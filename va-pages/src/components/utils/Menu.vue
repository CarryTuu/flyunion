<template>
	<el-menu
			default-active="2"
			class="el-menu-vertical-demo"
			:collapse="isCollapse"
			style="background: rgba(255, 255, 255, 0.65)"
	>
		<el-menu-item index="1">
			<el-icon><OfficeBuilding /></el-icon>
			<template #title><a href="/Company" class="href">航空公司</a></template>
		</el-menu-item>
		<el-menu-item index="2">
			<el-icon><Menu /></el-icon>
			<template #title><a href="/newFlight" class="href">开始新航班</a></template>
		</el-menu-item>
		<el-menu-item index="3">
			<el-icon><document /></el-icon>
			<template #title>Navigator Three</template>
		</el-menu-item>
		<el-menu-item index="4">
			<el-icon><setting /></el-icon>
			<template #title>Navigator Four</template>
		</el-menu-item>
		<el-menu-item @click="toggleCollapse">
			<el-icon>
				<component :is="isCollapse ? 'Expand' : 'Fold'" />
			</el-icon>
			<template #title>
				{{ isCollapse ? '展开菜单' : '折叠菜单' }}
			</template>
		</el-menu-item>
	</el-menu>
</template>
<script>
import {Menu, Setting, Location, Document, OfficeBuilding} from "@element-plus/icons-vue";

export default {
	components: {
		Location, Menu, Setting, Document, OfficeBuilding
	},
	name: 'SidebarMenu',
	data() {
		return {
			isCollapse: false
		}
	},
	methods: {
		toggleCollapse() {
			this.isCollapse = !this.isCollapse
		},
	}
}
</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
	width: 200px;
	min-height: 700px;
}
.href{
	text-decoration: none;
	color: #000;
}
</style>