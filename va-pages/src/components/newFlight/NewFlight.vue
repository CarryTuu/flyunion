<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu/></el-aside>
			<el-main>
				<el-card class="box-card-large">
					<div class="clearfix">
						<p class="text-center text-secondary">您可以在当前页面选择航班计划并且添加进您的计划执行表中，计划执行表将在您的主页可见</p>
						<p class="text-center text-secondary">如需连飞进行航班任务可直接复制航路填写航班计划</p>
					</div>
					<div class="clearfix">
						<p class="text-secondary">您可以在此输入起飞机场或者落地机场，亦可同时输入来精确查询航班</p>
						<el-form :inline="true" :model="searchForm">
							<el-form-item label="起飞机场">
								<el-input type="text" placeholder="起飞机场" v-model="searchForm.departure"/>
							</el-form-item>
							<el-form-item label="落地机场">
								<el-input type="text" placeholder="落地机场" v-model="searchForm.arrival"/>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" plain @click="search()">查询航班</el-button>
							</el-form-item>
							<el-form-item>
								<el-button type="danger" plain @click="getAllFlight()">取消搜索</el-button>
							</el-form-item>
						</el-form>
					</div>
					<el-table :data="flights">
						<el-table-column
								v-for="item in column.flightTable"
								:label="item.label"
								:prop="item.prop"
								:key="item.prop"
								:width="item.width"
						></el-table-column>
						<el-table-column label="操作">
							<template v-slot:default="scope">
								<el-button type="primary" plain @click="newPlan(scope.row)">选择航班任务</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-card>
			</el-main>
		</el-container>
	</div>
	<Footer></Footer>
</template>

<script>
import Bar from "@/components/utils/Bar.vue";
import Menu from "@/components/utils/Menu.vue";
import Footer from "@/components/utils/Footer.vue";
import va from "@/utils/va.js";

export default {
	components: {Footer, Bar, Menu,},
	data(){
		return {
			flights:[],
			cid: "",
			company: "",
			plan: {
				flightCode: "",
				departure: "",
				arrival: "",
				pilot: "",
			},
			searchForm: {
				departure: "",
				arrival: ""
			},
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
	created(){
		va.get("/user/loadLoginUser")
				.then(res => {
					this.cid = res.data.cid
					this.company = res.data.company
					this.getAllFlight()
				})
	},
	methods: {
		getAllFlight(){
			va.get("/flightPlan/queryByCompany/" + this.company)
					.then(res => {
						this.flights = res.data
						this.searchForm.departure = ""
						this.searchForm.arrival = ""
					})
		},
		newPlan(row){
			this.plan.flightCode = row.flightCode
			this.plan.departure = row.departure
			this.plan.arrival = row.arrival
			this.plan.pilot = this.cid
			va.post("/planList/newPlan", this.plan)
					.then(res => {
						this.$notify({
							title: '操作完毕',
							message: res.message,
							type: 'success',
							duration: 3000
						});
					})
		},
		search(){
			va.post("/flightPlan/airport", this.searchForm)
					.then(res => {
						this.flights = res.data
					})
		}
	}
}
</script>
