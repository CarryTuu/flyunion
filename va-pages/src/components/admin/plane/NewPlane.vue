<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<el-card class="box-card">
							<div slot="header">
								<span class="text-secondary">新增可用航空器</span>
							</div>
							<br>
							<br>
							<el-form v-model="planeForm">
								<el-form-item label="所有人">
									<el-input type="text" placeholder="所有者CID" v-model="planeForm.owner" />
								</el-form-item>
								<el-form-item label="所属航司">
									<el-select
											v-model="planeForm.company"
											placeholder="飞机落地航司"
									>
										<el-option
												v-for="item in companies"
												:key="item.id"
												:label="item.name"
												:value="item.id"
										/>
									</el-select>
								</el-form-item>
								<el-form-item label="所属机队">
									<el-input type="text" placeholder="所属机队" v-model="planeForm.fleet" />
								</el-form-item>
								<el-form-item label="飞机型号">
									<el-input type="text" placeholder="飞机型号" v-model="planeForm.model" />
								</el-form-item>
								<el-form-item label="停放位置">
									<el-input type="text" placeholder="飞机位置" v-model="planeForm.position" />
								</el-form-item>
								<br>
								<el-form-item>
									<el-button type="primary" plain style="margin-left: 180px">新增航空器</el-button>
								</el-form-item>
							</el-form>
						</el-card>
					</div>
					<div class="col-md-4"></div>
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
			planeForm: {
				owner: "",
				fleet: "",
				model: "",
				position: "",
				company: "",
			},
			companies: []
		}
	},
	methods: {
		getAllCompany(){
			va.get("/company/all")
					.then(res => {
						this.companies = res.data
					})
		}
	},
	created(){
		this.getAllCompany()
	}
}
</script>