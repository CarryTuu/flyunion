<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu/></el-aside>
			<el-main>
				<el-card class="box-card-large">
					<div slot="header">
						<p class="text-secondary">飞机详细信息</p>
					</div>
					<div class="row clearfix">
						<div class="col-md-2">
							<p class="text-center "><el-icon><Notebook /></el-icon>飞机注册号</p>
							<p class="text-center ">{{ plane.code }}</p>
						</div>
						<div class="col-md-2">
							<p class="text-center "><el-icon><OfficeBuilding /></el-icon>所属航司</p>
							<p class="text-center ">{{ companyName }}</p>
						</div>
						<div class="col-md-2">
							<p class=" text-center"><el-icon><Document /></el-icon>所属机队</p>
							<p class=" text-center">{{ plane.fleet }}</p>
						</div>
						<div class="col-md-2">
							<p class=" text-center"><el-icon><Document /></el-icon>机型</p>
							<p class=" text-center">{{ plane.model }}</p>
						</div>
						<div class="col-md-2">
							<p class=" text-center"><el-icon><Watch /></el-icon>飞行时长</p>
							<p class=" text-center">{{ plane.time }} min</p>
						</div>
						<div class="col-md-2">
							<p class=" text-center"><el-icon><Suitcase /></el-icon>归属飞行员</p>
							<p class=" text-center">{{ plane.owner }}</p>
						</div>
					</div>
					<br>
					<div class="row clearfix">
						<div class="col-md-12">
							<el-progress
									:text-inside="true"
									:stroke-width="20"
									:percentage="plane.durability"
									:status="stripped()"
									striped
							>
								<span>当前飞机维护情况：{{ plane.durability }}</span>
							</el-progress>
						</div>
					</div>
					<br>
				</el-card>
				<br>
				<div class="row clearfix">
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<el-button type="primary" plain round style="width: 200px" @click="isLogClicked = true">查看飞机飞行记录</el-button>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<el-button type="primary" plain round style="width: 200px" @click="isLogClicked = false">查看飞机维护记录</el-button>
					</div>
					<div class="col-md-2"></div>
				</div>
				<br>
				<el-card class="box-card-large" v-show="!isLogClicked">
					<div slot="header">
						<p class="text-secondary">飞机维护记录</p>
					</div>
					<el-table :data="maintainingRecord">
						<el-table-column
								v-for="item in columns.maintainingRecord"
								:label="item.label"
								:prop="item.prop">
						</el-table-column>
					</el-table>
				</el-card>
				<el-card class="box-card-large" v-show="isLogClicked">
					<div slot="header">
						<p class="text-secondary">飞机飞行记录</p>
					</div>
					<el-table :data="flightLog">
						<el-table-column
								v-for="item in columns.flightLog"
								:label="item.label"
								:prop="item.prop">
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
import Footer from "@/components/utils/Footer.vue";
import Menu from "@/components/utils/Menu.vue";
import va from "@/utils/va.js";
import {Notebook, Suitcase, Watch, Document, OfficeBuilding} from "@element-plus/icons-vue";

export default {
	components: {Menu, Footer, Bar, Notebook, Watch, Suitcase, Document, OfficeBuilding},
	data(){
		return {
			columns: {
				flightLog: [
					{
						label: "航班号",
						prop: "code",
					},
					{
						label: "执飞机长",
						prop: "pilot"
					},
					{
						label: "完成时间",
						prop: "formattedTime"
					},
					{
						label: "下降率(ft/min)",
						prop: "rate"
					},
					{
						label: "燃油消耗(kg)",
						prop: "oil"
					}
				],
				maintainingRecord: [
					{
						label: "注册号",
						prop: "code"
					},
					{
						label: "维护类别",
						prop: "type"
					},
					{
						label: "时间",
						prop: "formattedTime"
					}
				]
			},
			code: this.$route.params.code,
			plane: {},
			companyName: "",
			isLogClicked: true,
			flightLog: [],
			maintainingRecord: [],
		}
	},
	methods: {
		getPlaneDetail(){
			va.get("/plane/code/" + this.code)
					.then(res => {
						this.plane = res.data
						va.get("/company/getCompanyByID/" + res.data.company)
								.then(res => {
									this.companyName = res.data.name
								})
					})
		},
		getMaintainingRecord(){
			va.get("/maintainingRecord/" + this.code)
					.then(res => {
						this.maintainingRecord = res.data
					})
		},
		stripped(){
			if ( this.plane.durability >= 0 &&  this.plane.durability <= 20) {
				return 'exception'; // 红色
			} else if ( this.plane.durability > 20 &&  this.plane.durability <= 50) {
				return 'warning'; // 橙色
			} else if ( this.plane.durability > 50 &&  this.plane.durability <= 80) {
				return ''; // 蓝色（默认主题色）
			} else {
				return 'success'; // 绿色
			}
		},
		getFlightLog(){
			va.get("/flightLog/plane/" + this.code)
					.then(res => {
						this.flightLog = res.data
					})
		}
	},
	created(){
		this.getPlaneDetail()
		this.getFlightLog()
		this.getMaintainingRecord()
	}
}
</script>