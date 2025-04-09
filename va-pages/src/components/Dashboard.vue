<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-container>
				<el-aside width="200px"><Menu /></el-aside>
				<el-main>
					<div class="row clearfix">
						<div class="col-md-4"><el-card class="box-card">
							<div slot="header" class="clearfix">
								<span>个人信息</span>
								<el-button style="float: right; padding: 3px; font-size: 15px" type="text">修改信息</el-button>
							</div>
							<br>
							<div>
								<div class="row clearfix">
									<div class="col-md-4">
										<el-avatar :size="100" :src="avatar" />
									</div>
									<div class="col-md-8">
										<p>昵称：{{ loginUser.userName }}</p>
										<p>CID：{{ loginUser.cid }}</p>
										<p>呼号：{{ loginUser.callsign }}</p>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-md-4 text-center" >
										<el-icon><OfficeBuilding /></el-icon>航司
										<p>{{ companyName }}</p>
									</div>
									<div class="col-md-4 text-center">
										<el-icon><Handbag /></el-icon>职位
										<p>{{ job }}</p>
									</div>
									<div class="col-md-4 text-center">
										<el-icon><Timer /></el-icon>飞行时长
										<p>{{ loginUser.time }} min</p>
									</div>
									<div class="col-md-4 text-center">
										<el-icon><Van /></el-icon>飞机数量
										<p>{{ loginUser.planeCount }}架</p>
									</div>
									<div class="col-md-4 text-center">
										<el-icon><Coin /></el-icon>账户余额
										<p>￥ {{ loginUser.balance }}</p>
									</div>
									<div class="col-md-4 text-center">
										<el-icon><MapLocation /></el-icon>当前位置
										<p>{{ loginUser.airport }}</p>
									</div>
								</div>
							</div>
						</el-card>
						</div>
						<div class="col-md-4">
							<el-card class="box-card" style="height: 344px">
								<div slot="header" class="clearfix">
									<span>执飞排行榜</span>
								</div>
								<br>
								<el-table :data="topTenList" height="250">
									<el-table-column
											v-for="item in column.topTen"
											:key="item.prop"
											:label="item.label"
											:prop="item.prop">
									</el-table-column>
								</el-table>
							</el-card>
						</div>
						<div class="col-md-4">
							<el-card class="box-card" style="height: 344px">
								<div slot="header">
									<span>天气大屏</span>
								</div>
								<div class="row clearfix">
									<div class="col-md-6">
										<Circle/>
									</div>
									<div class="col-md-6">
										<p class="weatherDisplay">报文时间：{{ weather.observationTime }}</p>
										<p class="weatherDisplay" v-if="weather.wind.vrb">风向：不定</p>
										<p class="weatherDisplay" v-else>风向：{{ weather.wind.windDirection }}°</p>
									</div>
								</div>
							</el-card>
						</div>
					</div>
					<br>
					<div class="row clearfix">
						<div class="col-md-12">
							<el-card class="box-card-large">
								<div slot="header">
									<p class="text-center text-primary"><strong>航班信息大屏</strong></p>
								</div>
								<p class="text-center">实时在线航班</p>
								<p class="text-center">实时在线地图</p>
							</el-card>
						</div>
					</div>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
import Bar from "@/components/utils/Bar.vue";
import Menu  from "@/components/utils/Menu.vue";
import {Handbag, MapLocation, OfficeBuilding, Timer, Van, Coin} from "@element-plus/icons-vue";
import Map from "@/components/utils/Map.vue"
import va from "@/utils/va.js";
import {PositionMapper} from "@/utils/PositionMapper.js";
import Circle from "@/components/utils/Circle.vue";
import WeatherDisplay from "@/components/utils/WeatherDisplay.vue";

export default {
	components: {Circle, Menu, Bar, OfficeBuilding, Handbag, Timer, MapLocation, Van, Coin, Map, WeatherDisplay},
	data(){
		return {
			avatar: "",
			loginUser: {},
			companyName: "",
			job: "",
			topTenList: [],
			weather: {},
			column: {
				topTen: [
					{
						label: "昵称",
						prop: "userName"
					},
					{
						label: "CID",
						prop: "cid"
					},
					{
						label: "航空公司",
						prop: "company"
					},
					{
						label: "航班数",
						prop: "flightCount"
					}
				]
			}
		}
	},
	methods: {
		async loadLoginUser(){
			const res = await va.get("/user/loadLoginUser")
			this.loginUser = res.data
			this.avatar = "https://q.qlogo.cn/headimg_dl?dst_uin=" + this.loginUser.qq + "&spec=100&img_type=jpg"
			this.job = PositionMapper.getPosition(this.loginUser.job)
		},
		getTopTen(){
			va.get("/user/topTen")
					.then(res => {
						this.topTenList = res.data
					})
		},
		getCompanyName(){
			va.get("company/getCompanyByID/" + this.loginUser.company)
					.then(res => (
							this.companyName = res.data.name
					))
		},
		getWeather(){
			va.get("/metar/" + this.loginUser.airport)
					.then(res => {
						this.weather = res.data
					})
		}
	},
	created() {
		this.loadLoginUser()
		this.getTopTen()
		this.getCompanyName()
		this.getWeather()
	}
}
</script>
<style scoped>
.box-card {
	width: 480px;
	background: rgba(255, 255, 255, 0.65);
}
.box-card-large {
	background: rgba(255, 255, 255, 0.65);
}
.weatherDisplay{
	margin-top: 10px;
}
</style>