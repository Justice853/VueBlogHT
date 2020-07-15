<template>
    <div align="center" v-loading="loading">
       <div style="margin-top:50px;margin-right:50px;margin-left:50px;font-family: Laihu;">
        <el-table
            :data="list"
            style="width: auto;box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); "
            border
             :header-cell-style="{background:'#f5f7fa',color:'#606266'}"
            >
            <el-table-column
                label="id"
                type="index"
                width="50px"
                align="center">
            </el-table-column>
            <el-table-column
                prop="name"
                label="姓名"
                align="center"
                >
            </el-table-column>
            <el-table-column label="操作"  align="center" width="300px">
                <template slot-scope="scope" >
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)" style="font-family: Laihu;">编辑</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)" style="font-family: Laihu;">删除</el-button>
                </template>
            </el-table-column>
         </el-table>
         <div align="right" style="margin-top: 20px;">
            <el-button @click="handleNewEdit()" style="font-family: Laihu;">新增标签</el-button>
        </div>
        
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
        <el-dialog title="更改标签" :visible.sync="dialogForm2Visible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="updateform" :model="updateform" :rules="rules"  label-position="left"  >
                    <el-form-item label="标签名称"  prop="name" style="font-family: Laihu;">
                        <el-input type="text" v-model="updateform.name"  placeholder="请输入标签名称" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogForm2Visible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="update('updateform')" style="font-family: Laihu;">确 定</el-button>
                </div>
        </el-dialog>
        <el-dialog title="新增标签" :visible.sync="dialogFormVisible" style="font-family: Laihu;" :close-on-click-modal=false>
                <el-form ref="form" :model="form" :rules="rules"  label-position="left"  >
                    <el-form-item label="标签名称"  prop="name" style="font-family: Laihu;">
                        <el-input type="text" v-model="form.name"  placeholder="请输入标签名称" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false" style="font-family: Laihu;">取 消</el-button>
                    <el-button type="primary" @click="post('form')" style="font-family: Laihu;">确 定</el-button>
                </div>
        </el-dialog>
        
    </div>
</template>d
<script>
export default {
    data() {
        return {
            list:[],
            loading:false,
            pages:0,
            pageNum:1,
            hasNextPage:true,
            index:0,
            dialogForm2Visible:false,
            dialogFormVisible:false,
            updateform:{
                name:''
            },
            form:{
                name:''
            },
            rules:{
               name:[
                   {required: true, message: '请输入标签名称', trigger: 'blur' }
               ],
           }
        }
    },
    inject:['reload'],
    mounted() {
        this.loading = true;
        this.loadlist();
    },
    methods: {
        loadlist(){
            var _this=this
            this.$axios.get("/tags/?pageNum="+_this.pageNum)
            .then(res => {
                _this.loading=false
                console.log(res)
                if(res.status=== 200){
                    _this.list=res.data.list
                    _this.pages=res.data.pages
                    _this.pageNum=res.data.pageNum
                    _this.hasNextPage=res.data.hasNextPage
                }else {
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
              this.loadlist();
        },
        handleDelete(index, row){
            let _this =this
            console.log(row);
            this.$confirm('此操作将永久删除该标签, 是否继续?','提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            center: true
        }).then(()=>{
            this.$axios.get("/tags/"+row.id+"/delete")
            .then(res => {
                console.log(res)
                if(res.status === 200){
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
        }).catch(()=>{
            this.$message({
            type: 'info',
            message: '已取消'
          });
        });
    },
        handleEdit(index, row){
            this.updateform.name=row.name;
            this.updateform.id=row.id;
            this.dialogForm2Visible=true
        },
        update(updateform){
            var _this = this
            this.$refs[updateform].validate(valid=>{
                if(valid){
                    this.$axios.post("/tags/"+this.updateform.id,{
                        name:this.updateform.name,
                        id:this.updateform.id
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                            this.dialogForm2Visible = false;
                           _this.reload();
                        }else if(res.data.code === 202){
                             alert("不能添加相同的地址")
                        }else{
                            alert("服务器错误")
                        }
                    })
                    .catch(err => {
                        console.error(err); 
                    })
                }else{
                    alert("请输入完整内容")
                }
            })
        },
        handleNewEdit(){
            this.dialogFormVisible=true
        },
        post(form){
            var _this = this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/tags",{
                        name:this.form.name
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                            this.dialogFormVisible = false;
                           _this.reload();
                        }else if(res.data.code === 202){
                             alert("不能添加相同的地址")
                        }else{
                            alert("服务器错误")
                        }
                    })
                    .catch(err => {
                        console.error(err); 
                    })
                }else{
                    alert("请输入完整内容")
                }
            })

        }
        
    },
}
</script>
<style>
.bg-purple {
    background: #d3dce6;
  }
.grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

</style>