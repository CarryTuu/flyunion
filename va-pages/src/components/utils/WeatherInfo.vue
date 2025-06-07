<template>
	<div class="col-md-7" v-loading="loading">
		<p class="weatherDisplay">报文时间：{{ weather.observationTime }}</p>
		<p class="weatherDisplay" v-if="weather.wind.vrb === true">风向：不定</p>
		<p class="weatherDisplay" v-else>风向：{{ weather.wind.windDirection }}°</p>
		<p class="weatherDisplay">风速：{{ weather.wind.speed }} {{ weather.wind.speedUnit }}</p>
		<p class="weatherDisplay" v-if="weather.wind.gustSpeed">阵风风速：{{ weather.wind.gustSpeed }}</p>
		<p class="weatherDisplay" v-if="weather.wind.directionVariation">风向变化：{{ weather.wind.directionVariation }}</p>
		<p class="weatherDisplay" v-if="weather.cavok">能见度：大于10公里</p>
		<p class="weatherDisplay" v-else-if="weather.visibility.exceeds10km">能见度：大于10公里</p>
		<p class="weatherDisplay" v-else>能见度：{{ weather.visibility.value }}</p>
		<p class="weatherDisplay" v-if="weather.weatherConditions.length === 0">无影响飞行的天气</p>
		<p
				class="weatherDisplay"
				v-for="item in weather.weatherConditions"
				:key="item.descriptor"
		>
			天气强度：{{ item.descriptor }} {{ item.intensity }}
		</p>
		<p class="weatherDisplay" v-if="weather.cavok">无影响飞行的云层</p>
		<p
				class="weatherDisplay"
				v-else
				v-for="item in weather.clouds">
			云高：{{ item.altitude }}ft，云量：{{ item.coverage }} {{ item.type }}
		</p>
		<p class="weatherDisplay">
			温度：{{ weather.temperature.airTemp }} ℃
			露点：{{ weather.temperature.dewPoint }} ℃
		</p>
		<p class="weatherDisplay">修正海压：{{ weather.pressure.qnh}} {{ weather.pressure.qnhUnit }}</p>
		<p class="weatherDisplay" v-if="weather.trend === 'NOSIG'">未来两小时内无天气变化</p>
	</div>
</template>

<script>
import va from "@/utils/va.js";

export default {
	data(){
		return {
			loading: true,
			weather: {
				rawText: "",
				stationId: "",
				observationTime: "",
				wind: {
					windDirection: 0,
					speed: 0,
					gustSpeed: null,
					speedUnit: "",
					directionVariation: null,
					vrb: false
				},
				visibility: {
					value: 10000,
					exceeds10km: true,
					rawString: ""
				},
				weatherConditions: [],
				clouds: [],
				temperature: {
					airTemp: 0,
					dewPoint: 0
				},
				pressure: {
					qnh: 0,
					qnhUnit: "hPa"
				},
				trend: "NOSIG",
				cavok: true
			}
		}
	},
	methods: {
		async loadLoginUser(){
			const res = await va.get("/user/loadLoginUser");
			this.loginUser = res.data;
			const weatherInfo = await va.get("/metar/" + res.data.airport)
			this.weather = weatherInfo.data
			this.loading = false
		}
	},
	created(){
		this.loadLoginUser();
	}
}
</script>
<style>
.weatherDisplay{
	height: 13px
}
</style>