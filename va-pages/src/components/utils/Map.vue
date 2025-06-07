<template>
	<p class="text-center">选中航班信息</p>
	<el-table>
		<el-table-column label="航班号">{{ clickedPlane.code }}</el-table-column>
		<el-table-column label="飞行员">{{ clickedPlane.pilot }}</el-table-column>
		<el-table-column label="起飞机场">{{ clickedPlane.dep }}</el-table-column>
		<el-table-column label="落地机场">{{ clickedPlane.arr }}</el-table-column>
		<el-table-column label="高度">{{ clickedPlane.altitude }}</el-table-column>
		<el-table-column label="速度">{{ clickedPlane.speed }}</el-table-column>
	</el-table>
	<div id="mapContainer" class="map-container"></div>
</template>
<script>

import initializeMap from "@/utils/MapInitializer.js";
import mapbox from "mapbox-gl"

export default {
	data() {
		return {
			map: {},
			path: [],
			selectedPlane: {},
			clickedPlane: {},
			planes: [
				{
					pilot: "100013",
					code: "MF8000",
					dep: "ZSAM",
					arr: "ZSPD",
					route: "LJG B221 PAMVU V74 BK",
					speed: "408 kt",
					altitude: "30158 ft",
					lng: 121.334508,
					lat: 29.896228,
					planeList: "B737_800",
					angle: 192
				},
				{
					pilot: "100001",
					code: "MF8001",
					dep: "ZSPD",
					arr: "ZYTL",
					route: "ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA",
					speed: "423 kt",
					altitude: "30758 ft",
					lng: 121.620556,
					lat: 33.2525,
					planeList: "B737_800",
					angle: 335
				},
				{
					pilot: "100003",
					code: "3U5264",
					dep: "ZUUU",
					arr: "ZHHH",
					route: "UBRAB B213 VELPU W550 OTLOS V38 TOSIM B213 WTM",
					speed: "308 kt",
					altitude: "8900 ft",
					lng: 104.266167,
					lat: 30.933028,
					planeList: "A350_900",
					angle: 85
				},
			],
		}
	},
	async mounted() {
		this.map = await initializeMap("mapContainer")
		this.planes.forEach( plane => {
			this.drawMarker(plane)
		})
	},
	methods: {
		createImgElement(){
			const element = document.createElement("img");
			element.src = "/a35k.png";
			element.style.width = "35px";
			element.style.height = "35px";
			return element
		},
		drawMarker(plane){
			const marker = new mapbox.Marker({
				element: this.createImgElement()
			}).setLngLat([plane.lng, plane.lat]).setRotation(plane.angle).addTo(this.map)
			marker.getElement().addEventListener("click", (e) => {
				e.stopPropagation()
				this.clickedPlane = plane
			})
		}
	},
}
</script>
<style scoped>
.map-container {
	width: 100%;
	height: 750px;
}
</style>