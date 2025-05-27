<template>
	<div id="mapContainer" class="map-container"></div>
</template>
<script>
import initAMap, {newPolyLine} from "@/utils/AMapInitializer.js";

export default {
	data() {
		return {
			map: null,
			path: []
		}
	},
	mounted() {
		this.map = initAMap("mapContainer")
	},
	methods: {
		
		newPolyline(){
			newPolyLine(this.map, )
		}
	}
}
</script>
<style scoped>
.map-container {
	width: 100%;
	height: 750px;
}
</style>