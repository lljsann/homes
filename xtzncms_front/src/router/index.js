import Vue from 'vue'
import iView from 'iview'
import Util from '../libs/util'
import VueRouter from 'vue-router'
import {routers} from './router'

Vue.use(VueRouter)

// 路由配置
const RouterConfig = {
  /* mode: 'history', */
  routes: routers
}

export const router = new VueRouter(RouterConfig)

router.beforeEach((to, from, next) => {
  /*  iView.LoadingBar.start();
    Util.title(to.meta.title);
    next(); */
  if (to.meta.auth) { // 判断该路由是否需要登录权限
    if (localStorage.token) { // 获取当前的token是否存在
      iView.LoadingBar.start()
      Util.title(to.meta.title)
      next()
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  } else {
    iView.LoadingBar.start()
    Util.title(to.meta.title)
    next()
  }
})

router.afterEach((to) => {
  iView.LoadingBar.finish()
  window.scrollTo(0, 0)
})
