<template>
    <div class="m-container-small m-padded-tb-big">
        <el-form ref="form" :model="form" :rules="rules">
            <el-row>
                <el-col :span="2">
                    <el-form-item  prop="flag">
                        <el-select  v-model="form.flag" placeholder="类型">
                            <el-option label="原创" value="原创"></el-option>
                            <el-option label="转载" value="转载"></el-option>
                            <el-option label="翻译" value="翻译"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="22">
                    <el-form-item prop="title">
                        <el-input v-model="form.title"  placeholder="请选择标题"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item  >
                        <div id="md-content"  style="z-index: 2 !important;">
                            <textarea  v-model="form.content"  placeholder="博客内容" class="content" style="display: none">
                               
                            </textarea>
                        </div> 
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12" >
                    <el-form-item prop="type_id"  >
                        <el-select v-model="form.type_id" placeholder="请选择分类" style="width:98%; float:left;"  filterable >
                            <el-option
                            v-for="item in types"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item prop="tag_ids"> 
                        <el-select v-model="form.tag_ids"  placeholder="请选择标签" style="width:98%;float:right;" filterable multiple>
                            <el-option
                            v-for="item in tags"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item prop="first_picture">
                       <el-input v-model="form.first_picture"  placeholder="请输入首图地址"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item prop="description">
                       <el-input
                        type="textarea"
                        :rows="5"
                        placeholder="请输入概述内容"
                        v-model="form.description"
                        >
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12" >
                     <el-form-item prop="recommend" style="float:left;margin-right:20px;">
                            <el-checkbox v-model="form.recommend" label="推荐"></el-checkbox>
                    </el-form-item>
                     <el-form-item prop="share_statement" style="float:left;margin-right:20px;">
                             <el-checkbox v-model="form.share_statement" label="转载声明"></el-checkbox>
                    </el-form-item>         
                     <el-form-item prop="appreciation" style="float:left;margin-right:20px;">
                           <el-checkbox v-model="form.appreciation" label="赞赏"></el-checkbox>
                    </el-form-item>         
                     <el-form-item prop="commentabled" style="float:left;">
                            <el-checkbox v-model="form.commentabled" label="评论"></el-checkbox>
                    </el-form-item>                     
                </el-col>
            </el-row>
            <el-row style="float:right;" v-if="zhuanhuan">
                <el-col :span="8" style="padding-right:10px" >
                      <el-button type="primary" onclick="javascript:history.go(-1)">返回<i class="el-icon-upload2 el-icon--right"></i></el-button>
                </el-col>
                <el-col :span="8" style="padding-right:10px" >
                      <el-button type="primary" @click="baocun('form')">保存<i class="el-icon-edit el-icon--right"></i></el-button>
                </el-col>
                <el-col :span="8"  >
                      <el-button type="primary" @click="postBlog('form')">上传<i class="el-icon-upload el-icon--right" ></i></el-button>
                </el-col>
            </el-row>
            <el-row style="float:right;" v-if="!zhuanhuan">
                <el-col :span="8" style="padding-right:10px" >
                      <el-button type="primary" onclick="javascript:history.go(-1)">返回<i class="el-icon-upload2 el-icon--right"></i></el-button>
                </el-col>
                <el-col :span="8" style="padding-right:10px" >
                      <el-button type="primary" @click="updatebaocun('form')">保存<i class="el-icon-edit el-icon--right"></i></el-button>
                </el-col>
                <el-col :span="8"  >
                      <el-button type="primary" @click="updateblog('form')">更新<i class="el-icon-upload el-icon--right" ></i></el-button>
                </el-col>
            </el-row>

            
           
                
           
            
        </el-form>
        
        
    </div>
</template>

<script>


export default {
    data() {
        return {
            blogEditor:{},
            types: [{}],
            tags:[{}],
            zhuanhuan:true,
            id:-1,
            views:0,
            create_time:'',
            user_id:0,
            comment_count:0,
            form:{
                flag:'原创',
                title:'',
                type_id:'',
                tag_ids:[],
                first_picture:'',
                appreciation:false,
                share_statement:false,
                commentabled:false,
                recommend:false,
                description:'',
                content:''
            },
            rules:{
                flag:[
                    {required: true, message: '请选择类型', trigger: 'change'}
                ],
                title:[
                    {required: true, message: '请输入标题', trigger: 'blur'}
                ],
                type_id:[{
                    required: true, message: '请选择分类', trigger: 'change'
                }],
                tag_ids:[{
                    required: true, message: '请选择标签', trigger: 'change'
                }],
                first_picture:[{
                    required: true, message: '请输入首图', trigger: 'blur'
                }],
                description:[{
                    required: true, message: '请输入描述', trigger: 'blur'
                }],
                // contentarea:[{
                //      required: true, message: '请输入内容', trigger: 'blur'
                // }]
            },
        }
    },
    inject:['reload'],
    methods: {
        loadType(){
            var _this=this;
            this.$axios.get("/blogs/input/types")
            .then(res => {
                _this.types=res.data
                console.log(res)
            })
            .catch(err => {
                console.error(err); 
            })
        },
        loadTags(){
            var _this=this;
            this.$axios.get("/blogs/input/tags")
            .then(res => {
                _this.tags=res.data
                console.log(res)
            })
            .catch(err => {
                console.error(err); 
            })
        },
        
        postBlog(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/blogs",{
                        flag:this.form.flag,
                        title:this.form.title,
                        type_id:this.form.type_id,
                        tag_ids:(this.form.tag_ids).join(','),
                        first_picture:this.form.first_picture,
                        appreciation:this.form.appreciation,
                        share_statement:this.form.share_statement,
                        commentabled:this.form.commentabled,
                        recommend:this.form.recommend,
                        description:this.form.description,
                        content: _this.blogEditor.getMarkdown(),
                        published:true
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                           this.$router.push({path:'/blogList'})
                           _this.reload()
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
        baocun(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/blogs",{
                        flag:this.form.flag,
                        title:this.form.title,
                        type_id:this.form.type_id,
                        tag_ids:(this.form.tag_ids).join(','),
                        first_picture:this.form.first_picture,
                        appreciation:this.form.appreciation,
                        share_statement:this.form.share_statement,
                        commentabled:this.form.commentabled,
                        recommend:this.form.recommend,
                        description:this.form.description,
                        content: _this.blogEditor.getMarkdown(),
                        published:false
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                           this.$router.push({path:'/blogList'})
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
        loadblog(id){
            var _this=this
            var arrIntSet=[]
            var arrString =''
            _this.id=id;
            this.$axios.get("/blogs/"+id+"/input")
            .then(res => {
                _this.form.title=res.data.title
                _this.form.flag=res.data.flag
                _this.form.first_picture=res.data.first_picture
                _this.form.type_id=res.data.type_id
                
                var arrIntSet=[]
                var arrString =''
                arrString=(res.data.tag_ids).split(",");
                for(var arrInt in arrString){
                   arrIntSet.push(parseInt(arrString[arrInt]))
                }
                _this.form.tag_ids=arrIntSet
                _this.form.appreciation=res.data.appreciation
                _this.form.share_statement=res.data.share_statement
                _this.form.commentabled=res.data.commentabled
                _this.form.recommend=res.data.recommend
                _this.form.description=res.data.description
                _this.form.content=res.data.content
                _this.views=res.data.views
                _this.user_id=res.data.user_id
                _this.comment_count=res.data.comment_count
                _this.create_time=res.data.create_time
                console.log(res)
            })
            .catch(err => {
                console.error(err); 
            })
        },
        updateblog(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/blogs/"+_this.id,{
                        id:_this.id,
                        flag:this.form.flag,
                        title:this.form.title,
                        type_id:this.form.type_id,
                        tag_ids:(this.form.tag_ids).join(','),
                        first_picture:this.form.first_picture,
                        appreciation:this.form.appreciation,
                        share_statement:this.form.share_statement,
                        commentabled:this.form.commentabled,
                        recommend:this.form.recommend,
                        description:this.form.description,
                        content: _this.blogEditor.getMarkdown(),
                        published:true,
                        views:_this.views,
                        user_id:_this.user_id,
                        comment_count:_this.comment_count,
                        create_time:_this.create_time
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                           this.$router.push({path:'/blogList'})
                           _this.reload()
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
        updatebaocun(form){
            var _this=this
            this.$refs[form].validate(valid=>{
                if(valid){
                    this.$axios.post("/blogs/"+_this.id,{
                        id:_this.id,
                        flag:this.form.flag,
                        title:this.form.title,
                        type_id:this.form.type_id,
                        tag_ids:(this.form.tag_ids).join(','),
                        first_picture:this.form.first_picture,
                        appreciation:this.form.appreciation,
                        share_statement:this.form.share_statement,
                        commentabled:this.form.commentabled,
                        recommend:this.form.recommend,
                        description:this.form.description,
                        content: _this.blogEditor.getMarkdown(),
                        published:false,
                        views:_this.views,
                        user_id:_this.user_id,
                        comment_count:_this.comment_count,
                        create_time:_this.create_time
                    })
                    .then(res => {
                        console.log(res)
                        if(res.data.code === 200){
                           this.$router.push({path:'/blogList'})
                            _this.reload()
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

    },
    created() {
        var _this = this;
        _this.$nextTick(()=>{
            const blogEditor = editormd("md-content",{
                 width : "100%",
                 height: "600px",
                 syncScrolling : "single",
                 path    : "/static/editormd/lib/"
            })
            _this.blogEditor = blogEditor;
        })
    },
    mounted() {
        var _this = this
        var id = this.$route.query.id;
        if(id!=null){
            _this.zhuanhuan=false;
            _this.loadblog(id);
        }
        else{
            _this.zhuanhuan=true;
        }
        this.loadType();
        this.loadTags();

    },
}
</script>

<style>
.m-container-small {
    max-width: 60em !important;
    margin: auto !important;
}
.m-padded-tb-big {
    padding-top: 3em !important;
    padding-bottom: 3em !important;
}


</style>