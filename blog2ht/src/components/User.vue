<template>
  <div v-loading="loading">
        <div style="display: flex;flex-wrap: wrap;padding-top:20px;margin-left:40px " >
            <el-card style="width:300px;margin-top: 20px;margin-left: 50px;font-family: Laihu;" :body-style="{ padding: '0px' }" v-for="(user,index) in list" :key="index" shadow="hover" >
                <img :src="user.avatar"  class="image" style="height:190px;" >
                <div style="padding: 30px;">
                    <span>{{user.nickname}}</span>
                    <el-tag type="info" size="mini" v-if="user.type=='1'">管理员</el-tag>
                     <el-tag type="info" size="mini" v-if="user.type=='0'">普通用户</el-tag>
                <div class="bottom clearfix">
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                        <span>创建时间：<span>{{user.create_time}}</span></span>
                    </div>
                
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>邮箱：
                        <span>{{user.email}}</span>
                    </span>
                </div>
                
                    
                </div>
                </div>
            </el-card>   
     </div>
     
     
    <div align="center">
         <el-pagination
            background
            layout="prev, pager, next"
            :page-count="pages"
            :hide-on-single-page=!hasNextPage
            @current-change="handleCurrentChange"
            style="padding-top:50px;font-family: Laihu;">
     </el-pagination>   
     </div>
</div>
</template>

<script>
export default {
    data() {
        return {
           list:[],
           pages:0,
           pageNum:1,
           hasNextPage:true,
           loading: false,
        }
    },
    mounted() {
        this.loading = true;
        var _this=this
        this.loadUser();
    },
    inject:['reload'],
    methods: {
        loadUser(){
           var _this=this;
            this.$axios.get("/userList/?pageNum="+_this.pageNum)
            .then(res => {
                _this.loading = false;
                console.log(res.data.total)
                    if (res.status === 200) {
                        _this.list=res.data.list
                        _this.pages=res.data.pages
                        _this.pageNum=res.data.pageNum
                        _this.hasNextPage=res.data.hasNextPage
                        console.log(_this.pages)
                    } else {
                        _this.$message({type: 'error', message: '数据加载失败!'});
                    }
            })
            .catch(err => {
                console.error(err); 
            })
       },
       handleCurrentChange(currentpage){
              var _this=this
              _this.pageNum=currentpage
              this.loading = true;
              this.loadUser();
              
        }
    },
}
</script>

<style>
   .bottom {
    margin-top: 13px;
    line-height: s12px;
  }

  .button {
    padding: 0;
    float: right;
  }
  .button2 {
    padding: 0;
    float: left;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>