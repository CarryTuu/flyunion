<template>
	<div>
		<canvas ref="myCanvas"></canvas>
	</div>
</template>

<script>
import CircleRectDrawer from '@/utils/circleRectDrawer.js';
import axios from "axios";
import sqlite from "@/utils/sqlite.js";
import va from "@/utils/va.js";

export default {
	data() {
		return {
			runwayData: {
				secondaryRunway: "",
				primaryRunway: "",
				heading: 0
			},
			airport: ""
		}
	},
	async created() {
		await this.loadLoginUser()
		this.updateDrawing();
		
	},
	methods: {
		updateDrawing() {
			const canvas = this.$refs.myCanvas;
			CircleRectDrawer.initCanvas(canvas, 200, 230);
			
			CircleRectDrawer.draw(
					canvas,
					87.5,
					112.5,
					175,               // 圆直径
					115,               // 长方形长度
					15,                // 长方形宽度
					this.runwayData.heading     , // 旋转角度
					[this.runwayData.secondaryRunway, this.runwayData.primaryRunway],    // 两端数字
					{
						circleFill: 'rgba(255, 255, 255, 0.75)',
						circleStroke: 'rgba(255, 255, 255, 0.75)',
						rectFill: 'rgba(0, 0, 0, 0.75)',
						textColor: '#000',
						textSize: 20,
						textFont: 'Arial'
					}
			);
		},
		async loadLoginUser() {
			const res = await va.get("/user/loadLoginUser");
			await sqlite.get("/runwayEnd/getRunwayInfo/" + res.data.airport)
					.then(res => {
						this.runwayData = res.data
					})
		}
	}
}
</script>