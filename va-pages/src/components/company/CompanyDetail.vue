<template>
	<Bar/>
	<div class="common-layout">
		<el-container>
			<el-aside width="200px"><Menu /></el-aside>
			<el-main>
				<el-card class="box-card">
					<div slot="header" class="text-center">航空公司详细信息</div>
					<div class="row clearfix">
						<div class="col-md-8">
						
						</div>
						<div class="col-md-4">
							<br>
							<p><strong>航司名称： {{ company.name }}</strong></p>
							<p><strong>航司人数： {{ company.pilotCount }} 人</strong></p>
							<p><strong>机队规模： {{ company.planeCount }} 架</strong></p>
							<p><strong>资金余额： ￥ {{ company.balance }}</strong></p>
							<p><strong>航司基地： {{ company.base }}</strong></p>
						</div>
					</div>
				</el-card>
			</el-main>
		</el-container>
		<Footer />
	</div>
</template>
<script>
import va from "@/utils/va.js";
import WeatherInfo from "@/components/utils/WeatherInfo.vue";
import Menu from "@/components/utils/Menu.vue";
import Bar from "@/components/utils/Bar.vue";
import Footer from "@/components/utils/Footer.vue";
import {Coin, Handbag, MapLocation, OfficeBuilding, Timer, Van} from "@element-plus/icons-vue";
import Circle from "@/components/utils/Circle.vue";

export default {
	components: {Van, Handbag, Circle, OfficeBuilding, Timer, MapLocation, Coin, Footer, Bar, Menu, WeatherInfo},
	data(){
		return {
			id: this.$route.params.id,
			company: {}
		}
	},
	methods: {
		getCompanyDetail(){
			va.get("/company/getCompanyByID/" + this.id)
					.then(res => {
						this.company = res.data
					})
		}
	},
	created(){
		this.getCompanyDetail()
	}
}
</script>