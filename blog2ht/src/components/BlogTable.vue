<template>
    <div v-loading="loading">
        <div style="display: flex;flex-wrap: wrap;padding-top:20px;margin-left:40px " >
            <el-card style="width:300px;margin-top: 20px;margin-left: 50px;font-family: Laihu;" :body-style="{ padding: '0px' }" v-for="(blog,index) in list" :key="index" shadow="hover" >
                <img :src="blog.first_picture"  class="image" style="height:190px;" >
                <div style="padding: 30px;">
                    <span>{{blog.title}}</span>
                    <el-tag type="info" size="mini">{{blog.flag}}</el-tag>
                <div class="bottom clearfix">
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                        <span>更新时间：<span>{{blog.update_time}}</span></span>
                    </div>
                
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>描述：
                        <span>{{blog.description}}</span>
                    </span>
                </div>
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>浏览数：
                        <span>{{blog.views}}</span>
                    </span>
                    
                </div>
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span v-if="blog.published">
                        <el-tag type="success" effect="dark" size="mini">已发布</el-tag>
                    </span>
                  
                     <span v-if="blog.recommend">
                        <el-divider direction="vertical"></el-divider>
                        <el-tag type="success" effect="dark" size="mini">已推荐</el-tag>
                    </span>
                    
                </div>
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span v-if="blog.commentabled">
                        <el-tag  effect="dark" size="mini">评论</el-tag>
                    </span>
                    
                    <span v-if="blog.appreciation">
                        <el-divider direction="vertical"></el-divider>
                       
                        <el-tag  effect="dark" size="mini"> 赞赏</el-tag>
                        
                    </span>
                    
                    <span v-if="blog.share_statement">
                        <el-divider direction="vertical"></el-divider>
                        <el-tag  effect="dark" size="mini" > 分享</el-tag>
                        
                    </span>
                </div>
                <el-divider >编辑或删除</el-divider>
                <div align="center" style="color:#20a0ff;font-size: 12px;margin-top: 13px ">
                    <el-button type="text" class="button" @click="shanchu(blog.id)">删除</el-button>
                    <el-button type="text" class="button2" @click="bianji(blog.id)">编辑</el-button>
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
     mounted() {      
        this.loading = true;
        var _this=this
        if(this.show==0){
            this.loadBlog();
            
        }else if(this.show==1){
            this.loadPublishBlog();
            
        }
        else if(this.show==2){
             this.loadNoPublishBlog();
            
        }
        
      
    },
    
    data() {
        return {
           list:[],
           pages:0,
           pageNum:1,
           hasNextPage:true,
           loading: false,
           show_1:this.show
        }
    },
    inject:['reload'],
    methods: {
       loadBlog(){
           var _this=this;
            this.$axios.get("/blogList/?pageNum="+_this.pageNum)
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
       loadNoPublishBlog(){
           var _this=this;
            this.$axios.get("/blogNoPublishList/?pageNum="+_this.pageNum)
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
       loadPublishBlog(){
           var _this=this;
            this.$axios.get("/blogPublishList/?pageNum="+_this.pageNum)
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
               if(this.show==0){
                    this.loadBlog();
                    
                }else if(this.show==1){
                    this.loadPublishBlog();
                    
                }
                else if(this.show==2){
                    this.loadNoPublishBlog();
                    
                }
              
        }, 
        shanchu(id){
            var _this=this
        this.$confirm('此操作将永久删除该博客, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
            _this.$axios.get("/blogs/"+id+"/delete")
            .then(res => {
                if (res.status === 200){
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    _this.reload();
                }
            })
            .catch(err => {
                console.error(err); 
            })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
        },
        bianji(id){
            this.$router.push({path:'/postBlog', query: {id:id}})
        }
    },
    props: ['show']
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