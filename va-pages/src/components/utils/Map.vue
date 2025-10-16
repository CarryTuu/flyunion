<template>
	<p class="text-center">选中航班信息</p>
	<div class="row clearfix">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>航班号</th>
						<th>飞行员</th>
						<th>起飞机场</th>
						<th>落地机场</th>
						<th>高度</th>
						<th>速度</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>{{ clickedPlane.code }}</td>
						<td>{{ clickedPlane.pilot }}</td>
						<td>{{ clickedPlane.dep }}</td>
						<td>{{ clickedPlane.arr }}</td>
						<td>{{ clickedPlane.altitude }}</td>
						<td>{{ clickedPlane.speed }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="mapContainer" class="map-container"></div>
</template>
<script>

import initializeMap from "@/utils/MapInitializer.js";
import mapbox from "mapbox-gl"
import sqlite from "@/utils/sqlite.js";

export default {
	data() {
		return {
			currentLine: null,
			map: {},
			routeRequest: {
				dep: "",
				route: "",
				arr: ""
			},
			path: [],
			selectedPlane: {},
			clickedPlane: {},
			isLineDrawn: false,
			planes: [
				{
					pilot: "100013",
					code: "MF8000",
					dep: "ZYTL",
					arr: "ZSAM",
					route: "KARPI W5 TAO W174 XDX B221 ODULO V2 HSH G455 PK W116 JTN G204 SHZ B221 LJG A470 ENVEN",
					speed: "0 kt",
					altitude: "12 ft",
					lng: 121.519546508789,
					lat: 38.9671287536621,
					planeList: "B737_800",
					angle: 95
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
				this.drawLine(plane)
				this.currentLine = plane.pilot
				this.isLineDrawn = true
			})
		},
		async drawLine(plane){
			if (this.currentLine) {
				// 如果已经有线存在，删除旧线
				this.removeLine();
			}
			this.routeRequest.dep = plane.dep
			this.routeRequest.route = plane.route
			this.routeRequest.arr = plane.arr
			const res = await sqlite.post("/waypoint/getRoute", this.routeRequest)
			const coordinate = res.data
			this.map.addSource("route", {
				type: "geojson",
				data: {
					type: 'Feature',
					properties: {},
					geometry: {
						type: 'LineString',
						coordinates: coordinate
					}
				}
			});
			this.map.addLayer({
				id: "route",
				source: "route",
				type: "line",
				layout: {
					'line-join': 'round',
					'line-cap': 'round'
				},
				paint: {
					'line-color': "#2596be",
					'line-width': 4.5
				}
			})
		},
		removeLine() {
			if (this.map.getLayer('route')) {
				this.map.removeLayer('route');
			}
			if (this.map.getSource('route')) {
				this.map.removeSource('route');
			}
			this.isLineDrawn = false;
			this.currentLine = null;
		}
	},
}
</script>
<style scoped>
.map-container {
	width: 100%;
	height: 750px;
}
.table{
	background-color: rgba(255, 255, 255, 0.65);
}
</style>