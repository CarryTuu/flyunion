<template>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<div class="navbar-brand" style="margin-left: 50px">
				<a href="/Dashboard" style="text-decoration: none" class="text-info">FlyUnion VA服务后台管理系统</a>
			</div>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
					</li>
					<li class="nav-item">
					</li>
					<li class="nav-item">
					</li>
					<li class="nav-item">
					</li>
				</ul>
				<Clock />
				<span class="text-secondary" style="margin-right: 20px">欢迎您&nbsp; {{ loginUser.userName }}！</span>
			</div>
		</div>
	</nav>
</template>

<script>
import Menu from "@/components/utils/Menu.vue"
import Clock from "@/components/utils/Clock.vue"
import {Monitor} from "@element-plus/icons-vue";
import va from "@/utils/va.js";
import showUnauthorizedAlert from "@/utils/Unauthorized.js";
export default {
	components: {
		Menu,
		Clock,
		Monitor
	},
	data(){
		return {
			loginUser: {}
		}
	},
	methods: {
		async getLoginUser() {
			const loginUser = await va.get("/user/loadLoginUser")
			this.loginUser = loginUser.data
		},
		checkPermission(){
			if(this.loginUser.permission < 2){
				showUnauthorizedAlert()
				setTimeout(() => {
					window.location.href = "/Dashboard"
				}, 3000)
			}
		}
	},
	async created() {
		await this.getLoginUser()
		this.checkPermission()
	}
}
</script>