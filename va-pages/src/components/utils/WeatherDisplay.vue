<template>
	<p>时间：{{ weather.observationTime }}</p>
<!--	<p v-if="weather.wind.windDirection === 'VRB'">风向：VRB</p>-->
<!--	<p v-else>风向: {{ weather.wind.direction }} </p>-->
</template>

<script>
import va from "@/utils/va.js";

export default {
	data(){
		return {
			weather: {
				cavok: false,
				clouds: {},
				observationTime: "",
				pressure: {},
				rawText: "",
				stationId: "",
				temperature:{},
				trend: "",
				visibility: {},
				weatherConditions: [],
				wind: {}
			},
			loginUser: {}
		}
	},
	methods: {
		async loadLoginUser(){
			const res = await va.get("/user/loadLoginUser")
			this.loginUser = res.data

		}
	},
	created() {
		this.loadLoginUser()
	}
}
</script>