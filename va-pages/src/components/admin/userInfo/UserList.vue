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
								<span class="text-secondary">飞行员列表</span>
							</div>
							<br>
							<el-table :data="user">
								<el-table-column
										v-for="item in column.userTable"
										:label="item.label"
										:prop="item.prop"
								>
									<template  #default="{row}">
										<!-- 状态列的特殊处理 -->
										<template v-if="item.prop === 'status'">
											<el-tag :type="row.status === 'normal' ? 'success' : 'danger'" effect="dark" round>
												{{ row.status === 'normal' ? '正常' : '封禁' }}
											</el-tag>
										</template>
										<!-- 其他列正常显示 -->
										<template v-else>
											{{ row[item.prop] }}
										</template>
									</template>
								</el-table-column>
								<el-table-column label="操作" v-if="isBanButtonDisplay">
									<template #default="scope">
										<el-button type="danger" plain @click="banUser(scope.row.cid)">封禁用户</el-button>
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
			company: localStorage.getItem("company"),
			user: [],
			loginUser: {},
			isBanButtonDisplay: false,
			column: {
				userTable: [
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
						label: "飞行时长（分钟）",
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
		async getLoginUser(){
			const res = await va.get("/user/loadLoginUser")
			this.loginUser = res.data
			if(res.data.permission >= 4){
				this.isBanButtonDisplay = true
			}
		},
		getUserData(){
			va.get("/user/getAllUser")
					.then(res => {
						this.user = res.data
					})
		},
		banUser(cid){
			this.$confirm("此操作会将当前用户" + cid + "封禁，是否确定？", "警告", {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				va.put("/user/banUser/" + cid)
						.then(() => {
							this.$message.success("用户" + cid + "已经被封禁！如需解封请移步服务器数据库修改！")
							this.getUserData()
						})
			}).catch(() => {
				this.$message.info("取消操作！")
			})
		},
	},
	async created() {
		await this.getLoginUser()
		this.getUserData()
	}
}
</script>