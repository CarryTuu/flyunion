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
				<p class="text-info text-center">帮助中心</p>
				<el-form :model="form">
					<el-form-item>
						<el-input v-model="form.email" placeholder="请输入注册时填写的邮箱" type="text"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button :disabled="isButtonClicked" plain round style="width: 430px" type="primary"
						           @click="sendCaptcha">
							<span v-if="isButtonClicked">{{ remainingTime }}</span>
							{{ isButtonClicked ? "秒后可再次发送" : "发送邮件" }}
						</el-button>
					</el-form-item>
				</el-form>
				<el-form v-show="isCaptchaSent" :model="captchaForm">
					<el-form-item label="邮箱验证码">
						<el-input v-model="captchaForm.inputCaptcha" placeholder="请输入验证码" type="text"/>
					</el-form-item>
					<el-form-item>
						<el-button plain round type="primary" @click="verifyCaptcha()">提交验证码</el-button>
					</el-form-item>
				</el-form>
				<span class="text-left text-secondary">想起了密码？</span>
				<RouterLink to="/">回到登陆界面</RouterLink>
				<span class="text-secondary text-left">！</span>
			</div>
			<div class="end"></div>
		</div>
	</div>
</template>

<script>
import service from "@/utils/va.js";

export default {
	data() {
		return {
			isCaptchaSent: false,
			isButtonClicked: false,
			remainingTime: 0,
			form: {
				email: "",
			},
			captchaForm: {
				inputCaptcha: "",
				captchaKey: "",
			}
		}
	},
	methods: {
		sendCaptcha() {
			if (this.form.email === "") {
				this.$message.error("邮箱不可以为空！")
			} else {
				this.isCaptchaSent = true
				if (!this.isButtonClicked) {
					this.isButtonClicked = true;
					this.remainingTime = 60;
					this.timerId = setInterval(() => {
						if (this.remainingTime > 0) {
							this.remainingTime--;
						} else {
							clearInterval(this.timerId);
							this.isButtonClicked = false;
							this.remainingTime = 0;
						}
					}, 1000);
					service.post("/mail/sendCaptcha/" + this.form.email)
							.then(res => {
								localStorage.setItem("captchaKey", JSON.stringify(res.data))
							})
				}
			}
		},
		verifyCaptcha() {
			this.captchaForm.captchaKey = JSON.parse(localStorage.getItem("captchaKey"))
			service.post("/mail/verifyCaptcha", this.captchaForm)
					.then(res => {
						this.$message.success(res.message + "三秒后跳转到重置密码界面")
						localStorage.removeItem("captchaKey")
						localStorage.setItem("email", JSON.stringify(this.form.email))
						setTimeout(() => {
							// this.$router.post("/PasswordReset")
							this.$message.success("跳转了")
						}, 3000)
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
	margin-top: 400px
}
</style>
