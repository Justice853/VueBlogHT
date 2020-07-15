<template>
<div v-loading="loading">
    <div style="margin-top: 10px;display: flex;justify-content: center; float:right;">
      <el-button type="primary" icon="el-icon-edit" size="small" style="margin-left: 3px;font-family: Laihu;" @click="dialogFormVisible = true" >新增
      </el-button>
    </div>
     <el-dialog title="新增图片" :visible.sync="dialogFormVisible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="form" :model="form" :rules="rules"  label-position="left"  >
                    <el-form-item label="图片名称"  prop="picturename" style="font-family: Laihu;">
                        <el-input type="text" v-model="form.picturename"  placeholder="请输入图片名称" ></el-input>
                    </el-form-item>
                    <el-form-item label="图片地址"  prop="pictureaddress" style="font-family: Laihu;">
                          <el-input type="text" v-model="form.pictureaddress" ></el-input>
                    </el-form-item>
                    <el-form-item label="图片时间" prop="picturetime" style="font-family: Laihu;" >
                          <el-input type="text" v-model="form.picturetime"></el-input>
                    </el-form-item>
                    <el-form-item label="图片描述"  prop="picturedescription" style="font-family: Laihu;" >
                          <el-input type="textarea" v-model="form.picturedescription"></el-input>
                    </el-form-item>
                     
                </el-form>
                
                
                <div slot="footer" class="dialog-footer">
                    
                    <el-button @click="dialogFormVisible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="postPicture('form')" style="font-family: Laihu;">确 定</el-button>
                </div>
        </el-dialog>
     <div style="display: flex;flex-wrap: wrap;padding-top:50px;margin-left:40px ">
        <el-card style="width:300px;margin-top: 20px;margin-left: 50px;font-family: Laihu;" :body-style="{ padding: '0px' }" v-for="(image,index) in list" :key="index" shadow="hover" >
            <img :src="image.pictureaddress"  class="image" style="height:190px;" >
            <div style="padding: 30px;">
                <span>{{image.picturename}}</span>
              <div class="bottom clearfix">
               <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>上传时间：<span>{{image.picturetime}}</span></span>
                </div>
                
                <div style="text-align: left;color:black;font-size: 13px;margin-top: 13px;line-height: 15px;">
                    <span>描述：
                        <span>{{image.picturedescription}}</span>
                    </span>
                </div>
                <el-divider >编辑或删除</el-divider>
                <div align="center" style="color:#20a0ff;font-size: 12px;margin-top: 13px ">
                    <el-button type="text" class="button" @click="shanchu(image.id)">删除</el-button>
                    <el-button type="text" class="button2" @click="bianji(image)">编辑</el-button>
                </div>
                
              </div>
            </div>
        </el-card>

        <el-dialog title="编辑图片" :visible.sync="dialogForm2Visible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="updateform" :model="updateform" :rules="rules"  label-position="left"  >
                    <el-form-item label="图片名称"  prop="picturename" style="font-family: Laihu;">
                        <el-input type="text" v-model="updateform.picturename"  placeholder="请输入图片名称" ></el-input>
                    </el-form-item>
                    <el-form-item label="图片地址"  prop="pictureaddress" style="font-family: Laihu;">
                          <el-input type="text" v-model="updateform.pictureaddress" ></el-input>
                    </el-form-item>
                    <el-form-item label="图片时间" prop="picturetime" style="font-family: Laihu;" >
                          <el-input type="text" v-model="updateform.picturetime"></el-input>
                    </el-form-item>
                    <el-form-item label="图片描述"  prop="picturedescription" style="font-family: Laihu;" >
                          <el-input type="textarea" v-model="updateform.picturedescription"></el-input>
                    </el-form-item>
                     
                </el-form>
                
                
                <div slot="footer" class="dialog-footer">
                    
                    <el-button @click="dialogForm2Visible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="updatePicture('updateform')" style="font-family: Laihu;">确 定</el-button>
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
               picturedescription:'',
               picturename:'',
               picturetime:'',
               pictureaddress:''
           },
           updateform:{
               picturedescription:'',
               picturename:'',
               picturetime:'',
               pictureaddress:'',
               id:''
           },
           rules:{
               picturename:[
                   {required: true, message: '请输入图片名称', trigger: 'blur' }
               ],
               pictureaddress:[
                   {required: true, message: '请输入图片地址', trigger: 'blur' }
               ],
               picturetime:[
                   {required: true, message:"请输入图片时间", trigger: 'blur' }
               ],
               picturedescription:[
                   {required: true, message: '请输入图片描述', trigger: 'blur' }
                  
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
            this.$axios.get("/pictures/?pageNum="+_this.pageNum)
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
        this.$confirm('此操作将永久删除该图片, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
            _this.$axios.get("/deletepictures/"+id)
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
        postPicture(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.dialogFormVisible = false;
                    this.$axios.post("/postpictures",{
                        picturedescription:this.form.picturedescription,
                        picturename:this.form.picturename,
                        picturetime:this.form.picturetime,
                        pictureaddress:this.form.pictureaddress
                    })
                    .then(res => {
                        console.log(res)
                        if(res.status === 200){
                           _this.reload();
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
        bianji(image){
            this.updateform.picturename=image.picturename
            this.updateform.pictureaddress=image.pictureaddress
            this.updateform.picturetime=image.picturetime
            this.updateform.picturedescription=image.picturedescription
            this.updateform.id=image.id
            this.dialogForm2Visible = true;
        },
        updatePicture(updateform){
            var _this=this
            this.$refs[updateform].validate(valid=>{
                if(valid){
                    this.dialogForm2Visible = false;
                    this.$axios.post("/editpictures/"+this.updateform.id,{
                        picturedescription:this.updateform.picturedescription,
                        picturename:this.updateform.picturename,
                        picturetime:this.updateform.picturetime,
                        pictureaddress:this.updateform.pictureaddress,
                        id:this.updateform.id
                    })
                    .then(res => {
                        console.log(res.data)
                        if(res.status === 200){
                           _this.reload();
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