<template>
	<div ref="mapContainer" class="map-container"></div>
</template>
<script>
import {initializeMap} from "@/utils/MapInitializer.js";
import {loadWeather} from "@/utils/WeatherLoader";

export default {
	data() {
		return {
			map: null,
			isMapInitialized: false
		}
	},
	mounted() {
		this.map = initializeMap(this.$refs.mapContainer)
		this.map.on('load', () => {
			loadWeather(this.map)
			this.isMapInitialized = true;
		})
	},
}
</script>
<style scoped>
.map-container {
	width: 100%;
	height: 100vh;
}
</style>