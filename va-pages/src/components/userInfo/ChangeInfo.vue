<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="70px"><Menu/></el-aside>
			<el-main>
				<el-card>
					<div slot="header">
						<p class="text-secondary">你可以在此处更改您的密码，或者换绑您的邮箱，或者更改您的昵称</p>
					</div>
					<br>
					<div class="row clearfix">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<el-form :data="changeInfoForm">
								<el-form-item label="用户名">
									<el-input type="text" placeholder="用户名" v-model="changeInfoForm.userName"></el-input>
								</el-form-item>
								<el-form-item label="旧密码">
									<el-input type="password" show-password placeholder="旧密码" v-model="changeInfoForm.oldPassword"></el-input>
								</el-form-item>
								<el-form-item label="新密码">
									<el-input type="password" show-password placeholder="新密码" v-model="changeInfoForm.newPassword"></el-input>
								</el-form-item>
								<el-form-item label="确认新密码">
									<el-input type="password" show-password placeholder="确认新密码" v-model="changeInfoForm.confirmNewPassword"></el-input>
								</el-form-item>
								<el-form-item label="电子邮箱">
									<el-input type="text" placeholder="电子邮箱" v-model="changeInfoForm.email"></el-input>
								</el-form-item>
								<el-form-item label="邮箱验证码" v-show="isEmailButtonClicked">
									<el-input type="text" placeholder="验证码"></el-input>
								</el-form-item>
								<el-form-item>
									<el-button type="primary" plain :disabled="isEmailSent" style="width: 210px" @click="sendEmail()">
										<span v-if="isEmailSent">{{ remainingTime }}</span>
										{{ isEmailSent ? "秒后可再次发送" : "发送邮件" }}
									</el-button>
									<el-button type="primary" plain style="width: 210px" @click="changeInfo()">
										修改信息
									</el-button>
								</el-form-item>
							</el-form>
						</div>
						<div class="col-md-4"></div>
					</div>
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
			loginUser: {},
			changeInfoForm: {
				cid: "",
				userName: "",
				oldPassword: "",
				newPassword: "",
				confirmNewPassword: "",
				email: "",
			},
			isEmailButtonClicked: false,
			isEmailSent: false,
			remainingTime: 0
		}
	},
	methods: {
		async loadLoginUser(){
			const res = await va.get("/user/loadLoginUser")
			this.loginUser = res.data
		},
		isButtonClicked(){
			this.isEmailButtonClicked = true
		},
		sendEmail(){
			if (this.changeInfoForm.email === "") {
				this.$message.error("邮箱不可以为空！")
			} else {
				this.isEmailSent = true
				if (!this.isEmailButtonClicked) {
					this.isEmailButtonClicked = true;
					this.remainingTime = 60;
					this.timerId = setInterval(() => {
						if (this.remainingTime > 0) {
							this.remainingTime--;
						} else {
							clearInterval(this.timerId);
							this.isEmailSent = false;
							this.remainingTime = 0;
						}
					}, 1000);
					va.post("/mail/sendCaptcha/" + this.changeInfoForm.email)
				}
			}
		},
		changeInfo(){
			va.put("/user/changeInfo", this.changeInfoForm)
					.then(() => {
						this.$message.success("更改完毕，您无需重新登录，下次登录时需用新密码登录")
					})
		}
	},
	async created() {
		await this.loadLoginUser()
		this.changeInfoForm = this.loginUser
	}
}
</script>