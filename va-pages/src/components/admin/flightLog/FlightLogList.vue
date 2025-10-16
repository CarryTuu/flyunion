<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<el-card class="box-card-large">
					<div slot="header">
						<span class="text-secondary">需审批航班列表</span>
					</div>
					<br>
					<el-table :data="verifyLogs">
						<el-table-column
								v-for="item in column.flightLog"
								:label="item.label"
								:prop="item.prop"
						>
						</el-table-column>
						<el-table-column label="操作">
							<template #default="scope">
								<el-button type="primary" plain @click="routerToFlightLogDetail(scope.row)">查看报告详情</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-card>
			</el-main>
		</el-container>
	</div>
</template>

<script>
import Bar from "@/components/admin/util/Bar.vue";
import Menu from "@/components/admin/util/Menu.vue";
import va from "@/utils/va.js";

export default {
	components: {Bar, Menu},
	data(){
		return {
			company: {},
			verifyLogs: [],
			column: {
				flightLog: [
					{
						label: "航班号",
						prop: "code"
					},
					{
						label: "执飞飞机",
						prop: "plane"
					},
					{
						label: "执飞飞行员",
						prop: "pilot"
					},
					{
						label: "报告提交时间",
						prop: "formattedTime"
					},
					{
						label: "下降率（ft/min）",
						prop: "rate",
					},
					{
						label: "油量消耗（kg）",
						prop: "oil"
					}
				]
			}
		}
	},
	methods: {
		async getCompanyIata(){
			const company = localStorage.getItem("company")
			const res = await va.get("/company/getCompanyByID/" + company)
			this.company = res.data
		},
		getVerifyLog(){
			va.get("/flightLog/getVerifyLog/" + this.company.iata)
					.then(res => {
						this.verifyLogs = res.data
			})
		},
		routerToFlightLogDetail(row){
			this.$router.push({name: "FlightLogDetail", params: {id: row.id}})
		}
	},
	async created(){
		await this.getCompanyIata()
		this.getVerifyLog()
	}
}
</script>