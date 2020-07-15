<template>
  <div id="app">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
export default {
  name: 'App',
  provide :function() {
    return {
      reload:this.reload
    }
},
  data:function(){
    return {
        isRouterAlive:true
    }
},
methods: {
  reload:function(){
        this.isRouterAlive=false;
        this.$nextTick(function(){
            this.isRouterAlive=true
        })
    }
},
}
</script>

<style>
html,body{
     margin:0 ;
     padding:0 ;
}   
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;  
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
