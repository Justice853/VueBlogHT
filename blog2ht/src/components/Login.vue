<template>
  <div class="background">
    <el-form
      ref="loginform"
      :rules="rules"
      :model="form"
      label-position="left"
      class="login-container"
    >
      <h3 class="logintitle">六月博客管理系统登陆</h3>
      <el-form-item label="用户名" prop="username" style="font-family: Laihu;">
        <el-input type="text" v-model="form.username" placeholder="请输入账号" ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" style="font-family: Laihu;">
        <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-checkbox class="loginremember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button
          class="loginbutton"
          type="primary"
          @click="onSubmit('loginform')"
          style="width: 100%"
        >登录</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="提示" :visible.sync="dialogVisible" append-to-body  width="30%">
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="dialogLogin" append-to-body  width="30%">
      <span>用户名或密码错误</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogLogin = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import {mapMutations} from 'vuex';
export default {
  data() {
    return {
      form: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, message: "长度在6位以上", trigger: "blur" }
        ]
      },
      checked: true,
      dialogVisible: false,
      dialogLogin:false
    };
  },
  methods: {
    onSubmit(foreName) {
      var _this=this
      this.$refs[foreName].validate(valid => {
        if (valid) {    
           this.$axios.post('/login',{
             username:this.form.username,
             password:this.form.password
           })
           .then(res => {
             console.log(res.data.code)
             if(res.data.code === 200){
               _this.$store.commit('login',_this.form)
               this.$router.replace({path:'/'})
             }
             else{
               this.dialogLogin=true;
             }
           })
           .catch(err => {
             console.error(err); 
           })
        } else {
          this.dialogVisible = true;
          return false;
        }
      });
    }
  }
};
</script>
<style scoped>
.background {
  background: url("../assets/background.png");
  height: 100%;
  position: fixed;
  width: 100%;
}
.login-container {
  text-align: center;
  border: 1px solid #dcdfe6;
  border-radius: 15px;
  background-clip: padding-box;
  background: white;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  -webkit-box-orient: vertical;
  border: 1px solid #eaeaea;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  font-weight: 300;
  letter-spacing: 1px;
  line-height: 1.8;
}
.logintitle {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #666699;
  font-weight: 300;
  letter-spacing: 1px;
  line-height: 1.8;
  font-family: Laihu;
}
.loginremember {
  margin: 0px 0px 35px 0px;
  text-align: left;
  font-weight: 300;
  letter-spacing: 1px;
  line-height: 1.8;
  text-align: center;
  font-family: Laihu;
}
.loginbutton {
  font-weight: 300;
  letter-spacing: 1px;
  line-height: 1.4;
  font-family: Laihu;
}
</style>