<template>
	<Bar/>
	<div class="common-layout">
		<el-container>
			<el-aside width="200px"><Menu /></el-aside>
			<el-main>
				<el-card class="box-card" style="height: 800px">
					<div slot="header" class="text-center">航空公司详细信息</div>
					<br>
					<div class="row clearfix">
						<div class="col-md-8">
							<p class="text-center">航空公司机队信息</p>
							<el-collapse v-model="activeNames" accordion>
								<el-collapse-item
										v-for="(planes, fleetName) in resPlanes"
										:key="fleetName"
										:name="fleetName"
										:title="`${fleetName} (${planes.length}架)`"
								>
									<el-table :data="planes" border>
										<el-table-column
												v-for="item in columns.planeTable"
												:key="item.prop"
												:label="item.label"
												:prop="item.prop"
												:width="item.width"
										>
											<template #default="{row}">
												<!-- 状态列的特殊处理 -->
												<template v-if="item.prop === 'status'">
													<el-tag round effect="dark"
															:type="{
														        'available': 'success',
														        'in use': 'primary',
														        'crashed': 'danger'
														      }[row.status]"
													>
														{{
															row.status === 'available' ? '可用' :
																	row.status === 'in use' ? '使用中' :
																			'已坠毁'
														}}
													</el-tag>
												</template>
												<!-- 其他列正常显示 -->
												<template v-else>
													{{ row[item.prop] }}
												</template>
											</template>
										</el-table-column>
									</el-table>
								</el-collapse-item>
							</el-collapse>
							<br>
							<p class="text-center">航司飞行员列表</p>
							<br>
							<el-table :data="pilots">
								<el-table-column v-for="item in columns.pilotsTable"
								                 :label="item.label"
								                 :prop="item.prop"
								>
									<template v-if="item.prop === 'status'" #default="{row}">
										<el-tag :type="row.status === 'normal' ? 'success' : 'danger'" effect="dark" round>
											{{ row.status === 'normal' ? '正常' : '封禁' }}
										</el-tag>
									</template>
								</el-table-column>
							</el-table>
						</div>
						<div class="col-md-4">
							<p class="text-center">航空公司基本信息</p>
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
import service from "@/utils/va.js";

export default {
	components: {Van, Handbag, Circle, OfficeBuilding, Timer, MapLocation, Coin, Footer, Bar, Menu, WeatherInfo},
	data(){
		return {
			id: this.$route.params.id,
			company: {},
			activeNames: "",
			resPlanes: null,
			pilots: [],
			columns: {
				planeTable: [
					{
						label: "注册号",
						prop: "code"
					},
					{
						label: "所有人",
						prop: "owner"
					},
					{
						label: "机型",
						prop: "model"
					},
					{
						label: "总飞行时长",
						prop: "time"
					},
					{
						label: "当前状态",
						prop: "status"
					}
				],
				pilotsTable: [
					{
						label: "CID",
						prop: "cid"
					},
					{
						label: "用户名",
						prop: "userName"
					},
					{
						label: "呼号",
						prop: "callsign"
					},
					{
						label: "当前位置",
						prop: "airport"
					},
					{
						label: "飞行时长",
						prop: "time"
					},
					{
						label: "完成航班数",
						prop: "flightCount"
					},
					{
						label: "账户状态",
						prop: "status"
					}
				]
			}
		}
	},
	methods: {
		getCompanyDetail(){
			va.get("/company/getCompanyByID/" + this.id)
					.then(res => {
						this.company = res.data
					})
		},
		getPlanes(){
			service.get("/plane/getPlaneByCompany/" + this.id)
					.then(res => {
						this.resPlanes = res.data
					})
		},
		getUserByCompany(){
			service.get(`/user/getUserByCompany/${this.id}`)
					.then(res => {
						this.pilots = res.data
					})
		}
	},
	created(){
		this.getCompanyDetail()
		this.getPlanes()
		this.getUserByCompany()
	}
}
</script>