import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/login'
import Work from '@/components/work'
import WorkOutline from '@/components/workOutline'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/work',
      name: 'work',
      component: Work
    },
    {
      path: '/workOutline',
      name: 'workOutline',
      component: WorkOutline
    }
  ]
})
