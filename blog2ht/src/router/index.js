import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import BlogList from '@/components/BlogList'
import FriendsLink from '@/components/FriendsLink'
import Photo from '@/components/Photo'
import PostBlog from '@/components/PostBlog'
import Tags from '@/components/Tags'
import Types from '@/components/Types'
import User from '@/components/User'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: '登录',
      hidden: true,
      component: Login
    },
    {
      path:'/',
      name:'',
      component:Home,
      hidden:true,
      meta: {
        requireAuth: true
      }
    },{
      path:'/home',
      name:'文章管理',
      component:Home,
      iconCls: 'el-icon-edit',
      children:[
        {
          path:'/blogList',
          name:'文章列表',
          component:BlogList,
          meta:{
            keepAlive:true
          }
        },
        {
          path:'/postBlog',
          name:'发表文章',
          component:PostBlog,
          meta:{
            keepAlive:false
          }
        }
      ]
    },
    {
      path:'/home',
      component:Home,
      name:'分类与标签',
      iconCls: 'el-icon-document-copy',
      children:[
        {
          path:'/types',
          name:'分类管理',
          component:Types,
          meta:{
            keepAlive:true
          }
        },
        {
          path:'/tags',
          name:'标签管理',
          component:Tags,
          meta:{
            keepAlive:false
          }
        }
      ]
    },
    {
      path:'/home',
      component:Home,
      name:'友联管理',
      children:[
        {
          path:'/friendsLink',
          iconCls: 'el-icon-link',
          name:'友联管理',
          component:FriendsLink
        }
      ]
    },
    {
      path:'/home',
      component:Home,
      name:'相册管理',
      children:[
        {
          path:'/photo',
          iconCls: 'el-icon-video-camera',
          name:'图片管理',
          component:Photo
        }
      ]
    },
    {
      path:'/home',
      component:Home,
      name:'用户管理',
      children:[
        {
          path:'/user',
          iconCls: 'el-icon-user',
          name:'用户管理',
          component:User
        }
      ]
    }
  ]
})


