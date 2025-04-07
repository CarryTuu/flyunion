<template>
	<div class="container">
		<div class="row clearfix">
			<div class="start"></div>
			<div class="col-md-6">
				<br>
				<el-image src="logo-text.png"></el-image>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-4 login-form-container">
				<span class="text-secondary text-center">重置密码</span>
				<br>
				<br>
				<br>
				<el-form :data="passwordResetForm">
					<el-form-item label="新的密码">
						<el-input v-model="this.passwordResetForm.password" placeholder="new password" show-password
						          type="password"/>
					</el-form-item>
					<el-form-item label="确认密码">
						<el-input v-model="this.passwordResetForm.confirm" placeholder="confirm new password" show-password
						          type="password"/>
					</el-form-item>
					<br>
					<el-form-item>
						<el-button plain round style="width: 420px" type="primary" @click="changePassword">
							确认更改密码
						</el-button>
					</el-form-item>
				</el-form>
			</div>
			<div class="end"></div>
		</div>
	</div>
</template>

<script>
import service from "@/utils/va.js";

export default {
	data() {
		var pwdModify = (rule, value, callback) => {
			if (this.passwordResetForm.confirm !== this.passwordResetForm.password) {
				return callback(new Error('两次输入的密码不一致'))
			} else {
				callback()
			}
		}
		return {
			passwordResetForm: {
				password: "",
				confirm: ""
				// email: JSON.parse(localStorage.getItem("email"))
			},
			rules: {
				pwdModify: [
					{validator: pwdModify, trigger: 'blur'}
				]
			},
		}
	},
	methods: {
		changePassword() {
			service.post("/user/changePassword", this.passwordResetForm)
					.then(res => {
						this.$message.success(res.message)
						setTimeout(() => {
							localStorage.setItem("loginUser", JSON.stringify(res.data))
							this.$router.push("/Dashboard")
						}, 3000)
					})
					.catch(err => {
						this.$message.error(err.response.data.message)
					})
		}
	}
}
</script>
<style scoped>
.login-form-container {
	border: 1px solid #ccc;
	border-radius: 15px;
	padding: 20px;
	background-color: rgba(255, 255, 255, 0.7);
}

.start {
	margin-top: 250px;
}

.end {
	margin-top: 350px
}
</style>