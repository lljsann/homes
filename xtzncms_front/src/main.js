import Vue from 'vue'
import App from './App'
import store from './store'
import api from './libs/axios.js'
import { router } from './router/index'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import global from './libs/global.js'
import util from './libs/util.js'
import Vuex from 'vuex'
import echarts from 'echarts'
import 'babel-polyfill'
import VueQuillEditor from 'vue-quill-editor'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Vue.config.productionTip = false
Vue.config.devtools = true
Vue.use(iView)
Vue.use(Vuex)
Vue.use(VueQuillEditor)

Vue.prototype.$api = api
Vue.prototype.$global = global
Vue.prototype.$util = util
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

let v = new Vue({
  el: '#app',
  router,
  store: store,
  render: h => h(App),
  mounted () {
    this.$store.commit('refreshUser')
    this.$store.commit('updateMenulist')
  }
})

Vue.use({
  v
})
