<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu/></el-aside>
			<el-main>
				<el-card class="box-card">
					<div slot="header">
						<p class="text-secondary">通过下方单选框来筛选航空公司从而查询特定航司的飞机信息</p>
					</div>
					<el-select v-model="selectedCompany" style="width: 250px">
						<el-option
								v-for="item in companies"
								:key="item.id"
								:label="item.name"
								:value="item.id"
						/>
					</el-select>
					&nbsp;
					<el-button plain round type="primary" @click="getPlanesByCompany">查询</el-button>
					<br>
					<br>
					<el-collapse v-model="activeNames" accordion>
						<el-collapse-item
								v-for="(planes, fleetName) in resPlanes"
								:key="fleetName"
								:name="fleetName"
								:title="`${fleetName} (${planes.length}架)`"
						>
							<el-table :data="planes" border>
								<el-table-column
										v-for="item in column"
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
								<el-table-column label="操作">
									<template #default="{row}">
									<el-button plain round type="primary" @click="planeDetail(row)">查看飞机详情</el-button>
									</template>
								</el-table-column>
							</el-table>
						</el-collapse-item>
					</el-collapse>
				</el-card>
			</el-main>
		</el-container>
	</div>
	<Footer></Footer>
</template>

<script>
import Footer from "@/components/utils/Footer.vue";
import Bar from "@/components/utils/Bar.vue";
import Menu from "@/components/utils/Menu.vue";
import va from "@/utils/va.js";

export default {
	components: {Menu, Bar, Footer},
	data(){
		return {
			selectedCompany: "",
			companies: [],
			resPlanes: null,
			activeNames: "",
			column: [
				{
					label: "注册号",
					prop: "code"
				},
				{
					label: "所有者",
					prop: "owner"
				},
				{
					label: "所属机队",
					prop: "fleet"
				},
				{
					label: "机型",
					prop: "model",
				},
				{
					label: "飞行时长",
					prop: "time"
				},
				{
					label: "当前状态",
					prop: "status"
				}
			]
		}
	},
	created(){
		va.get("/company/all")
				.then(res => {
					this.companies = res.data
				})
	},
	methods: {
		getPlanesByCompany(){
			va.get("/plane/getPlaneByCompany/" + this.selectedCompany)
					.then(res => {
						this.resPlanes = res.data
					})
		},
		planeDetail(row){
			this.$router.push({name: "PlaneDetail", params: {code: row.code}})
		}
	}
}
</script>