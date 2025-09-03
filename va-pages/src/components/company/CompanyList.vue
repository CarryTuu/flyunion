<template>
	<Bar/>
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<el-card class="box-card">
					<br>
					<p class="text-center">您可以直接点击您想了解的航空公司的名称跳转至航司详情界面</p>
					<br>
					<el-table :data="companies">
						<el-table-column
								v-for="item in column"
								:label="item.label"
								:prop="item.prop">
						</el-table-column>
						<el-table-column label="操作">
							<template v-slot:default="scope">
								<el-button type="primary" plain @click="getCompanyDetail(scope.row)">查看航司详情</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-card>
			</el-main>
		</el-container>
		<Footer />
	</div>
</template>

<script>
import Bar from "@/components/utils/Bar.vue";
import Menu from "@/components/utils/Menu.vue";
import va from "@/utils/va.js";
import Footer from "@/components/utils/Footer.vue";

export default {
	components: {Bar, Menu, Footer},
	data(){
		return {
			companies:[],
			column: [
				{
					label: "航空公司名称",
					prop: "name"
				},
				{
					label: "机队规模",
					prop: "planeCount"
				},
				{
					label: "飞行员数量",
					prop: "pilotCount"
				},
				{
					label: "基地",
					prop: "base"
				}
			]
		}
	},
	methods: {
		getCompanies(){
			va.get("/company/all")
					.then(res => {
						this.companies = res.data
					})
		},
		getCompanyDetail(row){
			this.$router.push({name: "CompanyDetail", params: {id: row.id}})
		}
	},
	created(){
		this.getCompanies()
	}
}
</script>