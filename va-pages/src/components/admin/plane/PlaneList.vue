<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<el-card class="box-card-large">
					<div slot="header">
						<span class="text-secondary">可用航空器列表</span>
						<el-link style="float: right; padding: 3px; font-size: 15px" @click="routerToNewPlane()">可用航空器添加</el-link>
					</div>
					<br>
					<el-table :data="planeList" border>
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
						<el-table-column label="操作" >
							<template #default="{row}">
								<el-button plain round type="primary" v-if="isRestoreButtonDisplay" @click="restorePlaneStatus(row)">恢复飞机状态</el-button>
								<el-button plain round type="danger" v-if="isPublicButtonDisplay" @click="publicPlane(row)">调整为公用机</el-button>
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
			isRestoreButtonDisplay: false,
			isPublicButtonDisplay: false,
			planeList: [],
			column: [
				{
					label: "注册号",
					prop: "code",
					width: 120
				},
				{
					label: "所有者",
					prop: "owner",
					width: 170
				},
				{
					label: "所属机队",
					prop: "fleet",
					width: 250
				},
				{
					label: "机型",
					prop: "model",
					width: 250
				},
				{
					label: "位置",
					prop: "position",
					width: 150
				},
				{
					label: "飞行时长（分钟）",
					prop: "time",
					width: 150
				},
				{
					label: "当前状态",
					prop: "status",
					width: 100
				}
			]
		}
	},
	methods: {
		async getLoginUser(){
			const res = await va.get("/user/loadLoginUser")
			this.loginUser = res.data
			if(res.data.permission === 3){
				this.isRestoreButtonDisplay = true
			}
			if(res.data.permission === 4){
				this.isRestoreButtonDisplay = true
				this.isPublicButtonDisplay = true
			}
		},
		getAllPlane(){
			va.get("/plane/getAllPlane")
					.then(res => {
						this.planeList = res.data
					})
		},
		restorePlaneStatus(row){
			va.put("/plane/restorePlane/" + row.code)
					.then(() => {
						this.$message.success("调整完毕")
						this.getAllPlane()
					})
		},
		publicPlane(row){
			va.put("/plane/publicPlane/" + row.code)
					.then(() => {
						this.$message.success("调整完毕")
						this.getAllPlane()
					})
		},
		routerToNewPlane(){
			this.$router.push("/ADM/NewPlane")
		}
	},
	async created() {
		await this.getLoginUser()
		this.getAllPlane()
	}
}
</script>