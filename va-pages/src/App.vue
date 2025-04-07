<template>
	<Dashboard/>
</template>
<script>
import Dashboard from "@/components/Dashboard.vue";
import Map from "@/components/utils/Map.vue"
import Circle from "@/components/utils/Circle.vue";
export default {
	components: {Circle, Dashboard, Map},
	data() {
		return {}
	},
}
</script>