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
				<div class="container">
					<div class="row clearfix">
						<div class="col-md-6">
							<el-button plain round style="width: 170px" type="primary" @click="byCID">CID登录
							</el-button>
						</div>
						<div class="col-md-6">
							<el-button plain round style="width: 170px" type="primary" @click="byEmail">邮箱登录
							</el-button>
						</div>
					</div>
				</div>
				<hr>
				<br>
				<el-form v-show="loginMethods" :model="loginFormForCID">
					<el-form-item label="用户CID">
						<el-input v-model="loginFormForCID.cid" clearable placeholder="CID" type="text"></el-input>
					</el-form-item>
					<el-form-item label="登录密码">
						<el-input v-model="loginFormForCID.password" placeholder="password" show-password
						          type="password"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button plain round style="width: 420px" type="primary" @click="loginByCid">登录</el-button>
					</el-form-item>
				</el-form>
				<el-form v-show="!loginMethods" :model="loginFormForEmail">
					<el-form-item label="用户邮箱">
						<el-input v-model="loginFormForEmail.email" clearable placeholder="email"
						          type="text"></el-input>
					</el-form-item>
					<el-form-item label="登录密码">
						<el-input v-model="loginFormForEmail.password" placeholder="password" show-password
						          type="password"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button plain round style="width: 420px" type="primary" @click="loginByEmail">登录
						</el-button>
					</el-form-item>
				</el-form>
				<br>
				<span class="text-secondary">还未加入？</span>
				<RouterLink to="/Register">点击注册</RouterLink>
				<RouterLink style="margin-left: 150px" to="/Support">我无法登录！</RouterLink>
				<br>
			</div>
			<div class="end">
			</div>
		</div>
	</div>
</template>

<script>
import service from "@/utils/va.js";

export default {
	data() {
		return {
			loginMethods: true,
			loginFormForCID: {
				cid: "",
				password: "",
			},
			loginFormForEmail: {
				email: "",
				password: "",
			}
		}
	},
	methods: {
		byCID() {
			this.loginMethods = true
			this.loginFormForCID = {
				cid: "",
				password: "",
			}
		},
		byEmail() {
			this.loginMethods = false
			this.loginFormForEmail = {
				email: "",
				password: "",
			}
		},
		loginByCid() {
			service.post("/user/login/cid", this.loginFormForCID)
					.then(res => {
						this.$message.success(res.message)
						localStorage.setItem("loginUser", res.data)
						setTimeout(() => {
							this.$router.push("/Dashboard")
							this.$message.success("跳转成功")
						}, 3000)
					})
					.catch(err => {
						this.$message.error(err.response.data.message)
					})
		},
		loginByEmail() {
			service.post("/user/login/email", this.loginFormForEmail)
					.then(res => {
						this.$message.success(res.message)
						localStorage.setItem("loginUser", res.data)
						console.log("token已经存在了localStorage中")
						setTimeout(() => {
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
	margin-top: 300px
}
</style>
