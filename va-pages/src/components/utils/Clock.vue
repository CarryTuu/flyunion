<template>
	<div style="margin-right: 50px">
		UTC 时间：{{ utcTime }}
		&nbsp;&nbsp;
		北京时间：{{ beijingTime }}
	</div>
</template>

<script>
export default {
	name: 'TimeDisplay',
	data() {
		return {
			utcTime: '',
			beijingTime: '',
			timer: null
		}
	},
	methods: {
		formatTime(date) {
			return date.toISOString().replace('T', ' ').substring(0, 19)
		},
		updateTimes() {
			const now = new Date()
			
			// UTC 时间 - 自定义格式
			this.utcTime = now.getUTCHours().toString().padStart(2, '0') + ':' +
					now.getUTCMinutes().toString().padStart(2, '0') + ':' +
					now.getUTCSeconds().toString().padStart(2, '0')
			
			// 北京时间 (UTC+8) - 自定义格式
			const beijingTime = new Date(now.getTime() + 8 * 60 * 60 * 1000)
			this.beijingTime = beijingTime.getUTCHours().toString().padStart(2, '0') + ':' +
					beijingTime.getUTCMinutes().toString().padStart(2, '0') + ':' +
					beijingTime.getUTCSeconds().toString().padStart(2, '0')
		}
	},
	mounted() {
		this.updateTimes()
		// 每秒更新一次时间
		this.timer = setInterval(this.updateTimes, 1000)
	},
	beforeDestroy() {
		// 组件销毁前清除定时器
		if (this.timer) {
			clearInterval(this.timer)
		}
	}
}
</script>
