<template>
	<Bar />
	<div class="common-layout">
		<el-container>
			<el-aside width="200px"><Menu/></el-aside>
			<el-main>
				<el-card class="box-card">
				
				</el-card>
			</el-main>
		</el-container>
	</div>
	<Footer></Footer>
</template>

<script>
import Bar from "@/components/utils/Bar.vue";
import Menu from "@/components/utils/Menu.vue";
import Footer from "@/components/utils/Footer.vue";

export default {
	components: {Footer, Bar, Menu,},
	data(){
		return {
			flights:[],
		}
	},
	created(){
	
	},
	methods: {
		getAllFlight(){
			serivce.get()
		}
	}
}
</script>