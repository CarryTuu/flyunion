<template>
	<div>
		<canvas ref="myCanvas"></canvas>
	</div>
</template>

<script>
import CircleRectDrawer from '@/utils/circleRectDrawer.js';

export default {
	data() {
		return {
		
		}
	},
	mounted() {
		this.updateDrawing();
	},
	methods: {
		updateDrawing() {
			const canvas = this.$refs.myCanvas;
			CircleRectDrawer.initCanvas(canvas, 200, 230);
			
			CircleRectDrawer.draw(
					canvas,
					100,
					125,
					200,               // 圆直径
					130,               // 长方形长度
					20,                // 长方形宽度
					230     , // 旋转角度
					[5, 23],    // 两端数字
					{
						circleFill: 'rgba(255, 255, 255, 0.75)',
						circleStroke: 'rgba(255, 255, 255, 0.75)',
						rectFill: 'rgba(0, 0, 0, 0.75)',
						textColor: '#000',
						textSize: 20,
						textFont: 'Arial'
					}
			);
		}
	}
}
</script>