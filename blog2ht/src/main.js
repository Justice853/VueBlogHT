// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import axios from 'axios'
import vueAxios from 'vue-axios'
import 'element-ui/lib/theme-chalk/index.css'
import '../src/assets/css/font.css'
import editormd from '../static/editormd/editormd'
import '../static/editormd/css/editormd.css'
import Vuex from 'vuex'
import store from './store'

axios.defaults.baseURL = 'http://localhost:8092/api'
axios.defaults.withCredentials=true;
Vue.use(ElementUI,vueAxios,axios,Vuex,editormd);



Vue.prototype.$axios = axios

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.user.username) {
      next()
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
}
)



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})


