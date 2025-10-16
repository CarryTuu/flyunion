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
				<el-form :model="registerForm" :rules="rules">
					<el-form-item label="飞控CID">
						<el-input v-model="registerForm.cid" clearable placeholder="飞控CID" type="text"/>
					</el-form-item>
					<el-form-item label="用户昵称">
						<el-input v-model="registerForm.userName" clearable placeholder="用户昵称" type="text"/>
					</el-form-item>
					<el-form-item label="使用呼号">
						<el-input v-model="registerForm.callsign" clearable placeholder="使用呼号" type="text"/>
					</el-form-item>
					<el-form-item label="登录密码">
						<el-input v-model="registerForm.password" placeholder="登陆密码" show-password type="password"/>
					</el-form-item>
					<el-form-item label="确认密码" prop="pwdModify">
						<el-input v-model="registerForm.confirm" placeholder="确认密码" show-password type="password"/>
					</el-form-item>
					<el-form-item label="电子邮箱">
						<el-input v-model="registerForm.email" clearable placeholder="电子邮箱" type="text"/>
					</el-form-item>
					<el-form-item label="QQ账号">
						<el-input v-model="registerForm.qq" clearable placeholder="QQ账号" type="text"/>
					</el-form-item>
					<el-form-item label="航空公司">
						<el-select
								v-model="registerForm.company"
								class="m-2"
								placeholder="Select"
								style="width: 240px"
						>
							<el-option
									v-for="item in companies"
									:key="item.id"
									:label="item.name"
									:value="item.id"
							/>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button plain round style="width: 420px" type="primary" @click="register">确认注册</el-button>
					</el-form-item>
				</el-form>
				<br>
				<span class="text-secondary text-left">已有帐号？</span>
				<router-link to="/">前往登录</router-link>
				<br>
			</div>
			<div class="end">
			</div>
		</div>
	</div>
</template>

<script>
import service from "@/utils/va.js";
import va from "@/utils/va.js";

export default {
	data() {
		var pwdModify = (rule, value, callback) => {
			if (this.registerForm.confirm !== this.registerForm.password) {
				return callback(new Error('两次输入的密码不一致'))
			} else {
				callback()
			}
		}
		return {
			registerForm: {
				cid: "",
				userName: "",
				callsign: "",
				password: "",
				confirm: "",
				email: "",
				qq: "",
				company: ""
			},
			rules: {
				pwdModify: [
					{validator: pwdModify, trigger: 'blur'}
				]
			},
			companies: []
		}
	},
	methods: {
		register() {
			service.post("/user/register", this.registerForm)
					.then(res => {
						this.$message.success(res.message)
						localStorage.setItem("loginUser", res.data)
						setTimeout(() => {
							this.$message.success("跳转成功")
							this.$router.push("/Dashboard")
						}, 3000)
					})
		},
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