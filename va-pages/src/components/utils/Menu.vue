<template>
	<el-menu
			default-active="2"
			class="el-menu-vertical-demo"
			:collapse="isCollapse"
	>
		<el-menu-item index="1">
			<el-icon><location /></el-icon>
			<template #title>Navigator One</template>
		</el-menu-item>
		<el-menu-item index="2">
			<el-icon><Menu /></el-icon>
			<template #title>Navigator Two</template>
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
import {Menu, Setting, Location, Document} from "@element-plus/icons-vue";

export default {
	components: {
		Location, Menu, Setting, Document
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
	min-height: 400px;
}
</style>