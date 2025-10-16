<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<el-card class="box-card-large">
							<div slot="header">
								<span class="text-secondary">新可执行航线规划</span>
							</div>
							<br>
							<br>
							<el-form v-model="flightPlan">
								<el-form-item label="航班号">
									<el-input type="text" v-model="flightPlan.flightCode" />
								</el-form-item>
								<el-form-item label="起飞机场">
									<el-input type="text" v-model="flightPlan.departure" />
								</el-form-item>
								<el-form-item label="降落机场">
									<el-input type="text" v-model="flightPlan.arrival" />
								</el-form-item>
								<el-form-item label="巡航航路">
									<el-input type="textarea" v-model="flightPlan.route" />
								</el-form-item>
								<br>
								<el-form-item>
									<el-button type="primary" plain style="margin-left: 300px" @click="newFlightPlan()">确认建立航线</el-button>
								</el-form-item>
							</el-form>
						</el-card>
					</div>
					<div class="col-md-3"></div>
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
			flightPlan: {
				planId: "",
				flightCode: "",
				departure: "",
				arrival: "",
				route: "",
				company: localStorage.getItem("company")
			}
		}
	},
	methods: {
		newFlightPlan(){
			va.post("/flightPlan/", this.flightPlan)
					.then(() => {
						this.$message.success("航线建立完毕")
						this.$router.push("/ADM/FlightPlanList")
					})
		}
	}
}
</script>