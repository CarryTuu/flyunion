<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-12">
						<el-card class="box-card-large">
							<div slot="header">
								<span class="text-secondary">可执行航班编辑</span>
								<el-link style="float: right; padding: 3px; font-size: 15px" @click="routerToNewFlightPlan()">新建可执行航线</el-link>
							</div>
							<br>
							<br>
							<el-table :data="flightPlanList">
								<el-table-column
										v-for="item in column.flightTable"
										:label="item.label"
										:prop="item.prop"
										:width="item.width"
								></el-table-column>
								<el-table-column label="操作">
									<template #default="scope">
										<el-button type="primary" plain @click="routerToFlightPlanDetail(scope.row)">编辑信息</el-button>
										<el-button type="danger" plain @click="deletePlan(scope.row)">删除航班</el-button>
									</template>
								</el-table-column>
							</el-table>
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
import va from "@/utils/va.js";

export default {
	components: {Bar, Menu},
	data(){
		return {
			flightPlanList: [],
			column: {
				flightTable: [
					{
						label: "航班号",
						prop: "flightCode",
						width: 200
					},
					{
						label: "起飞机场",
						prop: "departure",
						width: 200
					},
					{
						label: "落地机场",
						prop: "arrival",
						width: 200
					},
					{
						label: "航路",
						prop: "route",
						width: 600
					}
				]
			}
		}
	},
	methods: {
		getFlightPlanByCompany(){
			const company = localStorage.getItem("company")
			va.get("/flightPlan/queryByCompany/" + company)
					.then(res => {
						this.flightPlanList = res.data
					})
		},
		deletePlan(row){
			this.$confirm("确定要删除航班吗？", "警告", {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				va.delete("/flightPlan/" + row.planId)
			}).catch(() => {
				this.$message.info("取消操作")
			})
		},
		routerToFlightPlanDetail(row){
			this.$router.push({name: "FlightPlanDetail", params: {id: row.planId}})
		},
		routerToNewFlightPlan(){
			this.$router.push("/ADM/NewFlightPlan")
		}
	},
	created(){
		this.getFlightPlanByCompany()
	}
}
</script>