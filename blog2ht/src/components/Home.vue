<template>
    <el-container class="container">
        <el-header>
            <div class="title">六月博客管理平台</div>
            <div class="userinfo">
                <el-dropdown @command="handleCommand">
                    <span class="el-dropdown-link home_userinfo" style="font-family:Oppo;">
                        {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown" style="font-family:Oppo;"> 
                        <el-dropdown-item>系统信息</el-dropdown-item>
                        <el-dropdown-item command="blog">博客主页</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-header>
            <el-container>
                <el-aside width="200px" >
                    <el-menu
                    default-active="0"
                    class="el-menu-vertical-demo"
                    text-color="#000000"
                    active-text-color="#954A45"
                    style="background-color: #ECECEC"
                    router >
                        <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
                            <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
                                <template slot="title">
                                    <i :class="item.iconCls"></i>
                                    <span style="font-family:Oppo;">{{item.name}}</span>
                                </template>
                                <el-menu-item v-for="child in item.children" v-if="!child.hidden" :index="child.path" :key="child.path" >
                                   <span style="font-family:Oppo;">{{child.name}}</span> 
                                </el-menu-item>
                            </el-submenu>
                            <template v-else>
                                <el-menu-item :index="item.children[0].path">
                                    <i :class="item.children[0].iconCls"></i>
                                    <span slot="title" style="font-family:Oppo;">{{item.children[0].name}}</span>
                                </el-menu-item>
                            </template>
                        </template>
                    </el-menu>
                </el-aside>
            <el-container>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item :to="{ path: '/home' }" style="font-family:Oppo;">首页</el-breadcrumb-item>
                        <el-breadcrumb-item v-text="this.$router.currentRoute.name" style="font-family:Oppo;"></el-breadcrumb-item>
                    </el-breadcrumb>
                    <keep-alive>
                        <router-view v-if="this.$route.meta.keepAlive"></router-view>
                    </keep-alive>
                    <router-view v-if="!this.$route.meta.keepAlive"></router-view>
                </el-main>
            </el-container>
        </el-container> 
    </el-container>
</template>
<script>
export default {
    methods: {
        handleCommand(command){
            var _this=this
            if(command == 'logout'){
                this.$confirm('注销登陆吗?','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(function(){
                     _this.$store.commit('removelogin')
                    _this.$router.replace({path: '/login'});
                })
            }else if(command == 'blog'){
                window.open("http://blog.cxp853.top")
            }
        }
    },
    mounted() {
        var _this=this
        console.log()
        _this.currentUserName= _this.$store.state.user.username
    },
    data() {
        return {
            currentUserName: ''
        }
    },
}
</script>
<style>
.container {
    position: absolute !important;
    top: 0px !important;
    left: 0px !important;
    width: 100% !important;
    height: 100% !important;
    /* background-color: rgba(235, 235, 235, 0.08) !important; */
  }
 .el-aside {
    background-color: #ECECEC;
  }
  .el-header {
    background-color: #DC9FB4 !important;
    height: 60px !important;
     -webkit-box-shadow: 0 4px 8px rgba(0,0,0,0.2) !important;
    box-shadow: 8px 4px 8px rgba(0,0,0,0.2) !important;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .title {
    /* color: #FEDFE1; */
    /* color: #B6473F; */
    /* color: #994639; */
    /* color: #F19483; */
    color: #954A45;
    font-size: 24px;
    display: flex;
    align-items: center;
    margin-left: 20px;
    height: 60px;
    font-weight: 300;
    letter-spacing: 1px;
    line-height: 1.4;
    font-family:Laihu;
    font-style: normal;
  }
  .userinfo{
    display: inline;
    margin-right: 20px;
    
    
  }
  .home_userinfo {
    color: #fff;
    cursor: pointer;
    
  }
 
  

</style>