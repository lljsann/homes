import Main from '@/views/Main.vue'

// 不作为Main组件的子页面展示的页面单独写，如下

export const loginRouter = {
  path: '/login',
  name: 'login',
  meta: {
    title: '协通智能科技网站后台管理系统 - 登录'
  },
  component: resolve => { require(['@/views/login.vue'], resolve) }
}

export const page404 = {
  path: '/*',
  name: 'error-404',
  meta: {
    title: '404-页面不存在'
  },
  component: resolve => { require(['@/views/error-page/404.vue'], resolve) }
}

export const page403 = {
  path: '/403',
  meta: {
    title: '403-权限不足'
  },
  name: 'error-403',
  component: resolve => { require(['@//views/error-page/403.vue'], resolve) }
}

export const page500 = {
  path: '/500',
  meta: {
    title: '500-服务端错误'
  },
  name: 'error-500',
  component: resolve => { require(['@/views/error-page/500.vue'], resolve) }
}

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
  path: '/',
  name: 'otherRouter',
  component: Main,
  meta: {
    auth: true // 添加该字段，表示进入这个路由是需要登录的
  },
  children: [
    {
      path: '/',
      title: '首页',
      name: 'home_index',
      meta: {auth: true},
      component: resolve => { require(['@/views/home/home.vue'], resolve) }
    },
    {
      path: 'home',
      title: '首页',
      name: 'home_index',
      meta: {auth: true},
      component: resolve => { require(['@/views/home/home.vue'], resolve) }
    }
  ]
}

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
  {
    path: '/home',
    icon: 'android-home',
    title: '首页管理',
    name: 'home',
    component: Main,
    meta: {
      auth: true // 添加该字段，表示进入这个路由是需要登录的
    },
    children: [
      // {
      //   path: 'info',
      //   title: '新闻资讯',
      //   name: 'infoManager',
      //   access: 'm-h-u-menu',
      //   meta: {auth: true},
      //   component: resolve => { require(['@/views/home/infoManager.vue'], resolve) }
      // },
      {
        path: 'office',
        title: '办公环境',
        access: 'm-h-d-menu',
        name: 'officeManager',
        meta: {auth: true},
        component: resolve => { require(['@/views/home/officeManager.vue'], resolve) }
      },
      {
        path: 'business',
        title: '智能板块',
        access: 'm-h-g-menu',
        name: 'businessManager',
        meta: {auth: true},
        component: resolve => { require(['@/views/home/businessManager.vue'], resolve) }
      },
      {
        path: 'cooperation',
        title: '合作单位',
        access: 'm-h-h-menu',
        name: 'cooperationManager',
        meta: {auth: true},
        component: resolve => { require(['@/views/home/cooperationManager.vue'], resolve) }
      }

    ]
  },
  {
    path: '/xietong',
    icon: 'ribbon-b',
    title: '关于协通',
    name: 'xietong',
    component: Main,
    meta: {
      auth: true // 添加该字段，表示进入这个路由是需要登录的
    },
    children: [
      {
        path: 'profile',
        title: '公司简介',
        access: 'm-x-p-menu',
        name: 'profileManager',
        meta: {
          auth: true
        },
        component: resolve => {
          require(['@/views/xietong/profileManager.vue'], resolve)
        }
      },
      // {
      //   path: 'structure',
      //   title: '公司架构',
      //   access: 'm-x-s-menu',
      //   name: 'structureManager',
      //   meta: {
      //     auth: true
      //   },
      //   component: resolve => {
      //     require(['@/views/xietong/structureManager.vue'], resolve)
      //   }
      // },
      {
        path: 'culture',
        title: '企业文化',
        access: 'm-x-c-menu',
        name: 'cultureManager',
        meta: {
          auth: true
        },
        component: resolve => {
          require(['@/views/xietong/cultureManager.vue'], resolve)
        }
      },
      {
        path: 'honor',
        title: '荣誉资质',
        access: 'm-x-h-menu',
        name: 'honorManager',
        meta: {
          auth: true
        },
        component: resolve => {
          require(['@/views/xietong/honorManager.vue'], resolve)
        }
      }
    ]
  },
  {
    path: '/dynamic',
    icon: 'planet',
    title: '最新动态',
    name: 'dynamic',
    component: Main,
    children: [
      {
        path: 'cnews',
        title: '公司新闻',
        access: 'm-d-c-menu',
        name: 'cnews',
        meta: {
          auth: true
        },
        component: resolve => { require(['@/views/dynamic/cnews.vue'], resolve) }
      }
      // },
      // {
      //   path: 'inews',
      //   title: '行业资讯',
      //   access: 'm-d-i-menu',
      //   name: 'inews',
      //   meta: {
      //     auth: true
      //   },
      //   component: resolve => { require(['@/views/dynamic/inews.vue'], resolve) }
      // }
    ]
  },
  {
    path: '/plate',
    icon: 'chatboxes',
    title: '业务板块',
    name: 'plate',
    component: Main,
    children: [
      {
        path: 'police',
        title: '智慧警务',
        name: 'police',
        access: 'm-p-p-menu',
        meta: {
          auth: true
        },
        component: resolve => { require(['@/views/plate/police.vue'], resolve) }
      },
      {
        path: 'government',
        title: '智慧政务',
        access: 'm-p-g-menu',
        name: 'government',
        meta: {
          auth: true
        },
        component: resolve => { require(['@/views/plate/government.vue'], resolve) }
      },
      {
        path: 'community',
        title: '智慧社区',
        access: 'm-p-c-menu',
        name: 'community',
        meta: {
          auth: true
        },
        component: resolve => { require(['@/views/plate/community.vue'], resolve) }
      }
    ]
  },
  {
    path: '/recruit',
    icon: 'android-contacts',
    title: '诚聘英才',
    access: 'm-r',
    name: 'recruit',
    component: Main,
    meta: {
      auth: true // 添加该字段，表示进入这个路由是需要登录的
    },
    children: [
      {
        path: 'recruitInfo',
        title: '诚聘英才',
        name: 'recruitInfo',
        access: 'm-r-r-menu',
        meta: {auth: true},
        component: resolve => { require(['@/views/recruit/recruitInfo.vue'], resolve) }
      }
    ]
  },
  {
    path: '/contact',
    icon: 'link',
    title: '联系我们',
    access: 'm-c',
    name: 'contact',
    component: Main,
    meta: {
      auth: true // 添加该字段，表示进入这个路由是需要登录的
    },
    children: [
      {
        path: 'contactInfo',
        title: '联系我们',
        name: 'contactInfo',
        access: 'm-c-c-menu',
        meta: {auth: true},
        component: resolve => { require(['@/views/contact/contactInfo.vue'], resolve) }
      }
    ]
  }
  // {
  //   path: '/new',
  //   icon: 'link',
  //   title: '测试',
  //   name: 'new',
  //   component: Main,
  //   meta: {
  //     auth: true // 添加该字段，表示进入这个路由是需要登录的
  //   },
  //   children: [
  //     {
  //       path: 'newInfo',
  //       title: '联系我们',
  //       name: 'newInfo',
  //       meta: {auth: true},
  //       component: resolve => { require(['@/views/new/newInfo.vue'], resolve) }
  //     }
  //   ]
  // }
]

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
  loginRouter,
  otherRouter,
  ...appRouter,
  page500,
  page403,
  page404
]
