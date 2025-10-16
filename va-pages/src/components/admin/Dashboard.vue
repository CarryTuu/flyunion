<template>
	<Bar/>
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu/></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-4">
						<el-card class="box-card-sm">
							<div slot="header" class="clearfix">
								<span class="text-secondary">
									航司飞行员规模
								</span>
							</div>
							<br>
							<p class="text-center">
								当前{{ company.name }}中共有{{ userCount }}名飞行员
							</p>
							<p class="text-center">
								点击左侧导航栏对应按钮编辑用户信息
							</p>
						</el-card>
					</div>
					<div class="col-md-4">
						<el-card class="box-card-sm">
							<div slot="header">
								<span class="text-secondary">可执行航线数量</span>
							</div>
							<br>
							<p class="text-center">
								当前{{ company.name }}中有{{ planCount }}条可执行航线
							</p>
							<p class="text-center">
								点击左侧导航栏对应按钮编辑可执行航线
							</p>
						</el-card>
					</div>
					<div class="col-md-4">
						<el-card class="box-card-sm">
							<div slot="header">
								<span>航司飞机数量</span>
							</div>
							<br>
							<p class="text-center">
								当前{{ company.name }}中有{{ planeCount }}架飞机
							</p>
							<p class="text-center">
								点击左侧导航栏对应按钮编辑飞机
							</p>
						</el-card>
					</div>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script>

import Menu from "@/components/admin/util/Menu.vue";
import Bar from "@/components/admin/util/Bar.vue";
import va from "@/utils/va.js";
import showUnauthorizedAlert from "@/utils/Unauthorized.js";

export default {
	components: {
		Menu,
		Bar,
	},
	data(){
		return {
			loginUser: {},
			company: {},
			planCount: 0,
			userCount: 0,
			planeCount: 0
		}
	},
	methods: {
		async getLoginUser(){
			const res = await va.get("/user/loadLoginUser");
			this.loginUser = res.data
			localStorage.setItem("company", res.data.company)
		},
		async getCompanyData(){
			const res = await va.get("/company/getCompanyByID/" + this.loginUser.company)
			this.company = res.data
		},
		async getPlanCount(){
			const res = await va.get("/flightPlan/planCount/" + this.loginUser.company)
			this.planCount = res.data
		},
		async getUserCount(){
			const res = await va.get("/user/getUserCountByCompany/" + this.loginUser.company)
			this.userCount = res.data
		},
		async getPlaneCount(){
			const res = await va.get("/plane/getPlaneCountByCompany/" + this.loginUser.company)
			this.planeCount = res.data
		}
	},
	async created(){
		await this.getLoginUser()
		await this.getCompanyData()
		await this.getPlanCount()
		await this.getUserCount()
		await this.getPlaneCount()
	}
}

</script>