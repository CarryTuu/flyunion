<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu /></el-aside>
			<el-main>
				<div class="row clearfix">
					<div class="col-md-3">
						<el-card class="box-card-sm">
							<div slot="header">
								<span class="text-secondary">
									空管服务费调整（单位：每次航班）
								</span>
								<br>
								<br>
								<el-form>
									<el-form-item>
										<el-input type="text" placeholder="请输入整数" v-model="atcFee" />
									</el-form-item>
									<br>
									<el-form-item>
										<el-button type="primary" style="margin-left: 120px" @click="editATCFee()" plain>提交修改</el-button>
									</el-form-item>
								</el-form>
							</div>
						</el-card>
					</div>
					<div class="col-md-3">
						<el-card class="box-card-sm">
							<div slot="header">
								<span class="text-secondary">
									航空煤油单价调整（单位：kg）
								</span>
								<br>
								<br>
								<el-form>
									<el-form-item>
										<el-input type="text" placeholder="请输入数字" v-model="oilCost" />
									</el-form-item>
									<br>
									<el-form-item>
										<el-button type="primary" style="margin-left: 120px" plain @click="editOilCost">提交修改</el-button>
									</el-form-item>
								</el-form>
								</div>
						</el-card>
					</div>
					<div class="col-md-3">
						<el-card class="box-card-sm">
							<div slot="header">
								<span class="text-secondary">
									停场费调整（单位：每天）
								</span>
								<br>
								<br>
								<el-form>
									<el-form-item>
										<el-input type="text" placeholder="请输入整数" v-model="parkFee" />
									</el-form-item>
									<br>
									<el-form-item>
										<el-button type="primary" style="margin-left: 120px" plain @click="editParkingFee">提交修改</el-button>
									</el-form-item>
								</el-form>
							</div>
						</el-card>
					</div>
					<div class="col-md-3">
						<el-card class="box-card-sm">
							<div slot="header">
								<span class="text-secondary">
									地勤服务费调整（单位：每次航班）
								</span>
								<br>
								<br>
								<el-form>
									<el-form-item>
										<el-input type="text" placeholder="请输入整数" v-model="groundMaintain"/>
									</el-form-item>
									<br>
									<el-form-item>
										<el-button type="primary" style="margin-left: 120px" plain @click="editGroundMaintain">提交修改</el-button>
									</el-form-item>
								</el-form>
							</div>
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
			atcFee: 0,
			groundMaintain: 0,
			oilCost: 0,
			parkFee: 0,
			requestForm: {
				key: "",
				value: 0
			}
		}
	},
	methods: {
		editATCFee(){
			this.requestForm.key = "ATC_fee"
			this.requestForm.value = this.atcFee
			va.put("/constants/", this.requestForm)
					.then(() => {
						this.$message.success("修改完成！")
						this.$router.push("/ADM/Constants")
					})
		},
		editParkingFee(){
			this.requestForm.key = "park_fee"
			this.requestForm.value = this.parkFee
			va.put("/constants/", this.requestForm)
					.then(() => {
						this.$message.success("修改完成！")
						this.$router.push("/ADM/Constants")
					})
		},
		editOilCost(){
			this.requestForm.key = "oil_cost"
			this.requestForm.value = this.oilCost
			va.put("/constants/", this.requestForm)
					.then(() => {
						this.$message.success("修改完成！")
						this.$router.push("/ADM/Constants")
					})
		},
		editGroundMaintain(){
			this.requestForm.key = "ground_maintain"
			this.requestForm.value = this.groundMaintain
			va.put("/constants/", this.requestForm)
					.then(() => {
						this.$message.success("修改完成！")
						this.$router.push("/ADM/Constants")
					})
		}
	}
}
</script>