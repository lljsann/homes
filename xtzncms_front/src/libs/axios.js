import {router} from './../router/index'
import global from './global'
import iView from 'iview'
import store from './../store'
// 配置API接口地址
var root = global.serverPath
// 引用axios
var axios = require('axios')
// 自定义判断元素类型JS
function toType (obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}
// 参数过滤函数
function filterNull (o) {
  for (var key in o) {
    if (o[key] === null) {
      delete o[key]
    }
    if (toType(o[key]) === 'string') {
      o[key] = o[key].trim()
    } else if (toType(o[key]) === 'object') {
      o[key] = filterNull(o[key])
    } else if (toType(o[key]) === 'array') {
      o[key] = filterNull(o[key])
    }
  }
  return o
}

function apiAxios (method, url, params, success, failure) {
  if (params) {
    params = filterNull(params)
  }
  if (method === 'GET') {
    if (params == null) {
      params = {
        rd: Date.now()
      }
    } else {
      params.rd = Date.now()
    }
  }
  console.log(url)
  axios({
    method: method,
    url: encodeURI(url),
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: true,
    headers: {
      'token': store.state.user.token ? store.state.user.token : null
    }
  })
    .then(function (res) {
      if (res.data.rspCode === 801 || res.data.rspCode === 807) {
        store.commit('logout')
        router.push({
          name: 'login',
          query: {code: res.data.rspCode}
        })
      } else if (res.data.rspCode === 813) {
        iView.Message.error('无权限')
      } else {
        success(res.data)
      }
    })
    .catch(function (err) {
      let res = err.response
      if (err) {
        failure(res)
        console.log('api error, info: ' + err)
      }
    })
}

// 返回在vue模板中的调用接口
export default {
  get: function (url, params, success, failure) {
    return apiAxios('GET', url, params, success, failure)
  },
  post: function (url, params, success, failure) {
    return apiAxios('POST', url, params, success, failure)
  },
  put: function (url, params, success, failure) {
    return apiAxios('PUT', url, params, success, failure)
  },
  delete: function (url, params, success, failure) {
    return apiAxios('DELETE', url, params, success, failure)
  }
}
