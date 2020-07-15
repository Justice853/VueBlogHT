<template>
    <el-container class="blogList">
        <el-main class="main">
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                <el-tab-pane label="全部文章" name="first">
                    <blogtable show=0></blogtable>
                </el-tab-pane>
                <el-tab-pane label="已发表" name="second">
                    <blogtable show=1></blogtable>
                </el-tab-pane>
                <el-tab-pane label="草稿箱" name="third">
                    <blogtable show=2></blogtable>
                </el-tab-pane>
            </el-tabs>
        </el-main>
    </el-container>

</template>
<script>
import BlogTable from '@/components/BlogTable'
export default {
     data() {
      return {
        activeName: 'first',
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
        
      },
      
    },
    components:{
        'blogtable':BlogTable
    },
    
    
}
</script>
<style>

</style>