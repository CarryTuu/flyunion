<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-8">
						<el-card class="box-card-large">
							<div slot="header">
								<span class="text-secondary">航班运行轨迹</span>
							</div>
							<div id="mapContainer"></div>
						</el-card>
					</div>
					<div class="col-md-4">
						<el-card class="box-card-large">
							<div slot="header">
								<span class="text-secondary">航班信息</span>
							</div>
							<p class="text-center">航班报告基本信息</p>
							<br>
							<p><strong>航班号： {{ flightLog.code }}</strong></p>
							<p><strong>执飞飞机： {{ flightLog.plane }} </strong></p>
							<p><strong>执飞飞行员： {{ flightLog.pilot }}</strong></p>
							<p><strong>起飞机场： {{ flightLog.departure }}</strong></p>
							<p><strong>落地机场： {{ flightLog.arrival }}</strong></p>
							<p><strong>飞行航路： {{ flightLog.route }}</strong></p>
							<p><strong>下降率： {{ flightLog.rate }} ft/min</strong></p>
							<p><strong>油量消耗： {{ flightLog.oil }} kg</strong></p>
							<br>
							<hr>
							<p class="text-secondary">审核区域</p>
							<el-button type="success" plain style="width: 147px" @click="acceptLog">通过该报告</el-button>
							<el-button type="warning" plain style="width: 147px" @click="rejectLog">拒绝该报告</el-button>
							<el-button type="danger" plain style="width: 147px" @click="deleteLog">删除该报告</el-button>
						</el-card>
					</div>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script>
import Bar from "@/components/admin/util/Bar.vue";
import Menu from "@/components/admin/util/Menu.vue";
import initializeMap from "@/utils/MapInitializer.js";
import va from "@/utils/va.js";
import sqlite from "@/utils/sqlite.js";


export default {
	components: {Bar, Menu},
	data(){
		return {
			flightLog: {},
			id: this.$route.params.id,
			map: {},
			line: null,
			routeRequest: {
				dep: "",
				route: "",
				arr: ""
			},
		}
	},
	methods: {
		async getFlightLog(){
			const res = await va.get("/flightLog/id/" + this.id)
			this.flightLog = res.data
		},
		async drawRoute(){
			this.routeRequest.dep = this.flightLog.departure
			this.routeRequest.arr = this.flightLog.arrival
			this.routeRequest.route = this.flightLog.route
			const res = await sqlite.post("/waypoint/getRoute", this.routeRequest)
			const coordinate = res.data
			this.map.addSource("route", {
				type: "geojson",
				data: {
					type: 'Feature',
					properties: {},
					geometry: {
						type: 'LineString',
						coordinates: coordinate
					}
				}
			});
			this.map.addLayer({
				id: "route",
				source: "route",
				type: "line",
				layout: {
					'line-join': 'round',
					'line-cap': 'round'
				},
				paint: {
					'line-color': "#2596be",
					'line-width': 4.5
				}
			})
		},
		acceptLog(){
			va.put("/flightLog/accept/" + this.id)
					.then(res => {
						this.$message.success(res.message)
						this.$router.push("/ADM/FlightLogList")
					})
		},
		rejectLog(){
			va.put("/flightLog/reject/" + this.id)
					.then(res => {
						this.$message.success(res.message)
						this.$router.push("/ADM/FlightLogList")
					})
		},
		deleteLog(){
			this.$confirm("此操作会将报告从数据库中删除并不计入飞行数据，确认吗？", "警告", {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				va.delete("/flightLog/delete/" + this.id)
						.then(res => {
							this.$message.success(res.message)
							this.$router.push("/ADM/FlightLogList")
						})
			}).catch(() => {
				this.$message.info("取消操作")
			})
		}
	},
	async mounted() {
		await this.getFlightLog()
		this.map = await initializeMap("mapContainer")
		await this.drawRoute()
		
	}
}
</script>
<style>
#mapContainer {
	width: 100%;
	height: 750px;
}
</style>