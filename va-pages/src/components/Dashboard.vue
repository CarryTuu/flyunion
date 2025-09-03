<template>
	<Bar/>
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-4">
						<el-card class="box-card">
							<div slot="header" class="clearfix">
								<span>个人信息</span>
								<el-link style="float: right; padding: 3px; font-size: 15px" class="text-primary">修改信息</el-link>
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
								<br>
								<br>
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
						<el-card class="box-card">
							<div slot="header" class="clearfix">
								<span>执飞排行榜</span>
							</div>
							<br>
							<el-table :data="topTenList" height="330" style="">
								<el-table-column
										v-for="item in column.topTen"
										:key="item.prop"
										:label="item.label"
										:prop="item.prop"
										:width="item.width">
								</el-table-column>
							</el-table>
						</el-card>
					</div>
					<div class="col-md-4">
						<el-card class="box-card">
							<div slot="header">
								<span>天气大屏</span>
							</div>
							<div class="row clearfix">
								<div class="col-md-5">
									<Circle/>
								</div>
								<WeatherInfo/>
							</div>
						</el-card>
					</div>
				</div>
				<br>
				<div class="row clearfix">
					<div class="col-md-12">
						<el-card class="box-card-large">
							<div slot="header">
								<p>已计划航班列表</p>
							</div>
							<el-table :data="plannedFlight">
								<el-table-column
										v-for="item in column.plannedFlight"
										:key="item.prop"
										:label="item.label"
										:prop="item.prop"
										:width="item.width">
								</el-table-column>
							</el-table>
						</el-card>
					</div>
				</div>
				<br>
				<div class="row clearfix">
					<div class="col-md-12">
						<el-card class="box-card-large">
							<div slot="header">
								<p>航班信息大屏</p>
							</div>
							<p class="text-center">实时在线航班</p>
							<el-table v-model:data="planes">
								<el-table-column
										v-for="item in column.flight"
										:label="item.label"
										:prop="item.prop">
								</el-table-column>
							</el-table>
							<br>
							<Map/>
						</el-card>
					</div>
				</div>
			</el-main>
		</el-container>
		<Footer />
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
import WeatherInfo from "@/components/utils/WeatherInfo.vue";
import Footer from "@/components/utils/Footer.vue"

export default {
	components: {Circle, Menu, Bar, OfficeBuilding, Handbag, Timer, MapLocation, Van, Coin, Map, WeatherInfo, Footer},
	data(){
		return {
			avatar: "",
			loginUser: {},
			companyName: "",
			job: "",
			topTenList: [],
			plannedFlight: [],
			column: {
				plannedFlight: [
					{
						label: "航班号",
						prop: "flightCode"
					},
					{
						label: "起飞机场",
						prop: "departure"
					},
					{
						label: "落地机场",
						prop: "arrival"
					},
					{
						label: "计划时间",
						prop: "formattedTime"
					},
					{
						label: "当前状态",
						prop: "status"
					}
				],
				topTen: [
					{
						label: "昵称",
						prop: "userName",
						width: 100
					},
					{
						label: "CID",
						prop: "cid",
						width: 100

					},
					{
						label: "航空公司",
						prop: "company"
					},
					{
						label: "航班数",
						prop: "flightCount"
					}
				],
				flight: [
					{
						label: "航班号",
						prop: "code"
					},
					{
						label: "飞行员",
						prop: "pilot"
					},
					{
						label: "起飞机场",
						prop: "dep"
					},
					{
						label: "落地机场",
						prop: "arr"
					},
					{
						label: "高度",
						prop: "attitude"
					},
					{
						label: "速度",
						prop: "speed"
					},
				]
			},
			planes: [
				{
					pilot: "100013",
					code: "MF8000",
					dep: "ZSAM",
					arr: "ZSPD",
					route: "LJG B221 PAMVU V74 BK",
					speed: "408 kt",
					attitude: "30158 ft",
					lng: 121.334508,
					lat: 29.896228,
					planeList: "B737_800",
					angle: 192
				},
				{
					pilot: "100001",
					code: "MF8001",
					dep: "ZSPD",
					arr: "ZYTL",
					route: "ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA",
					speed: "423 kt",
					attitude: "30758 ft",
					lng: 121.620556,
					lat: 33.2525,
					planeList: "B737_800",
					angle: 335
				},
				{
					pilot: "100003",
					code: "3U5264",
					dep: "ZUUU",
					arr: "ZHHH",
					route: "UBRAB B213 VELPU W550 OTLOS V38 TOSIM B213 WTM",
					speed: "308 kt",
					attitude: "8900 ft",
					lng: 104.266167,
					lat: 30.933028,
					planeList: "A350_900",
					angle: 85
				},
			],
		}
	},
	methods: {
		async loadLoginUser() {
			const res = await va.get("/user/loadLoginUser");
			res.data.password = "********"
			this.loginUser = res.data;
			this.avatar = "https://q.qlogo.cn/headimg_dl?dst_uin=" + this.loginUser.qq + "&spec=100&img_type=jpg";
			this.job = PositionMapper.getPosition(this.loginUser.job);
			
			// 使用 Promise.all 并行请求，但确保所有数据加载完成
			const [companyRes] = await Promise.all([
				va.get("company/getCompanyByID/" + this.loginUser.company),
			]);
			this.companyName = companyRes.data.name;
		},
		getTopTen() {
			va.get("/user/topTen")
					.then(res => {
						this.topTenList = res.data
					})
		},
		getPlannedFlight(){
			va.get("/plannedFlight/" + this.loginUser.cid)
					.then(res => {
						this.plannedFlight = res.data
					})
		}
	},
	async created() {
		await this.loadLoginUser()
		this.getTopTen()
		this.getPlannedFlight()
	}
}
</script>