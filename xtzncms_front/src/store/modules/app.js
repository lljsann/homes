import {otherRouter, appRouter} from '@/router/router'

const app = {
  state: {
    menuList: [],
    routers: [
      otherRouter,
      ...appRouter
    ],
    currentPath: [
      {
      }
    ],
    currentPageName: ''
  },
  mutations: {
    updateMenulist (state) {
      let menuList = []
      appRouter.forEach((item, index) => {
        if (item.children.length === 1) {
          menuList.push(item)
        } else {
          let temp = item.children
          let childrenArr = []
          for (var i = 0; i < temp.length; i++) {
            if (temp[i].access !== undefined) {
              childrenArr.push(temp[i])
            }
          }
          if (childrenArr.length !== 0) {
            item.children = childrenArr
            menuList.push(item)
          }
        }
      })
      state.menuList = menuList
    },
    setCurrentPath (state, pathArr) {
      state.currentPath = pathArr
    }
  }
}

export default app
