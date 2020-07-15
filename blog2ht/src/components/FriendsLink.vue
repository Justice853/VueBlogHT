<template>
<div v-loading="loading">
    <div style="margin-top: 10px;display: flex;justify-content: center; float:right;">
      <el-button type="primary" icon="el-icon-edit" size="small" style="margin-left: 3px;font-family: Laihu;" @click="dialogFormVisible = true" >新增
      </el-button>
    </div>
     <el-dialog title="新增友联" :visible.sync="dialogFormVisible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="form" :model="form" :rules="rules"  label-position="left"  >
                    <el-form-item label="友联名称"  prop="blogname" style="font-family: Laihu;">
                        <el-input type="text" v-model="form.blogname"  placeholder="请输入图片名称" ></el-input>
                    </el-form-item>
                    <el-form-item label="友联地址" prop="blogaddress" style="font-family: Laihu;" >
                          <el-input type="text" v-model="form.blogaddress" placeholder="请输入友联地址"></el-input>
                    </el-form-item> 
                    <el-form-item label="友联图片地址"  prop="pictureaddress" style="font-family: Laihu;">
                          <el-input type="text" v-model="form.pictureaddress" placeholder="请输入友联图片地址" ></el-input>
                    </el-form-item>
                     
                </el-form>
                
                
                <div slot="footer" class="dialog-footer">
                    
                    <el-button @click="dialogFormVisible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="postFriend('form')" style="font-family: Laihu;">确 定</el-button>
                </div>
        </el-dialog>
     <div style="display: flex;flex-wrap: wrap;padding-top:50px;margin-left:40px ">
        <el-card style="width:300px;margin-top: 20px;margin-left: 50px;font-family: Laihu;" :body-style="{ padding: '0px' }" v-for="(friend,index) in list" :key="index" shadow="hover" >
            <img :src="friend.pictureaddress"  class="image" style="height:190px;" >
            <div style="padding: 30px;">
                <span>
                <el-link :href="friend.blogaddress" type="primary">{{friend.blogname}}</el-link></span>
              <div class="bottom clearfix">
               <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>上传时间：<span>{{friend.create_time}}</span></span>
                </div>
                
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>具体地址：
                        <span>{{friend.blogaddress}}</span>
                    </span>
                </div>
                <el-divider >编辑或删除</el-divider>
                <div align="center" style="color:#20a0ff;font-size: 12px;margin-top: 13px ">
                    <el-button type="text" class="button" @click="shanchu(friend.id)">删除</el-button>
                    <el-button type="text" class="button2" @click="bianji(friend)">编辑</el-button>
                </div>
                
              </div>
            </div>
        </el-card>

        <el-dialog title="编辑图片" :visible.sync="dialogForm2Visible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="updateform" :model="updateform" :rules="rules"  label-position="left"  >
                    <el-form-item label="友联名称"  prop="blogname" style="font-family: Laihu;">
                        <el-input type="text" v-model="updateform.blogname"  placeholder="请输入友联名称" ></el-input>
                    </el-form-item>
                    <el-form-item label="友联地址" prop="blogaddress" style="font-family: Laihu;" >
                          <el-input type="text" v-model="updateform.blogaddress" placeholder="请输入友联地址" ></el-input>
                    </el-form-item>
                    <el-form-item label="友联图片地址"  prop="pictureaddress" style="font-family: Laihu;">
                          <el-input type="text" v-model="updateform.pictureaddress" placeholder="请输入友联图片地址" ></el-input>
                    </el-form-item>
                    
                </el-form>
                
                
                <div slot="footer" class="dialog-footer">
                    
                    <el-button @click="dialogForm2Visible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="updateFriend('updateform')" style="font-family: Laihu;">确 定</el-button>
                </div>
        </el-dialog>
         
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
        this.loadPicture();
    },
    
    data() {
        return {
           form:{
               blogaddress:'',
               blogname:'',
               pictureaddress:''
           },
           updateform:{
               blogaddress:'',
               blogname:'',
               pictureaddress:'',
               id:''
           },
           rules:{
               blogname:[
                   {required: true, message: '请输入友联名称', trigger: 'blur' }
               ],
               pictureaddress:[
                   {required: true, message: '请输入图片地址', trigger: 'blur' }
               ],
               blogaddress:[
                   {required: true, message:"请输入友联地址", trigger: 'blur' }
               ],
           },
           list:[],
           pages:0,
           pageNum:1,
           hasNextPage:true,
           loading: false,
           dialogFormVisible:false,
           dialogForm2Visible:false
        };
    },
    inject:['reload'],
    methods: {
        loadPicture(){
            var _this=this;
            this.$axios.get("/friendlinks/?pageNum="+_this.pageNum)
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
              this.loadPicture();
        },
        shanchu(id){
            var _this=this
        this.$confirm('此操作将永久删除该友链, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
            _this.$axios.get("/friendlinks/"+id+"/delete")
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
        postFriend(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/friendlinks",{
                        blogname:this.form.blogname,
                        blogaddress:this.form.blogaddress,
                        pictureaddress:this.form.pictureaddress
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                            this.dialogFormVisible = false;
                           _this.reload();
                        }else if(res.data.code === 202){
                            alert("不能添加相同的地址")
                        }
                        else{
                            alert("服务器错误")
                        }
                    })
                    .catch(err => {
                        console.error(err); 
                    })
                }
                else{
                    alert("请输入完整内容")
                }
            })
        },
        bianji(friend){
            this.updateform.blogname=friend.blogname
            this.updateform.blogaddress=friend.blogaddress
            this.updateform.pictureaddress=friend.pictureaddress
            this.updateform.id=friend.id
            this.dialogForm2Visible = true;
        },
        updateFriend(updateform){
            var _this=this
            this.$refs[updateform].validate(valid=>{
                if(valid){
                    this.$axios.post("/friendlinks/"+this.updateform.id,{
                        blogname:this.updateform.blogname,
                        blogaddress:this.updateform.blogaddress,
                        pictureaddress:this.updateform.pictureaddress,
                        id:this.updateform.id
                    })
                    .then(res => {
                        console.log(res.data)
                        if(res.data.code === 200){
                            this.dialogForm2Visible = false;
                           _this.reload();
                        }else if(res.data.code === 202){
                            alert("不能添加相同的地址")
                        }
                        else{
                            alert("服务器错误")
                        }
                    })
                    .catch(err => {
                        console.error(err); 
                    })
                }
                else{
                    alert("请输入完整内容")
                }
            })
        }

        
        
    }
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