
const user = {
  state: {
    token: '',
    account: ''
  },
  mutations: {
    logout (state, vm) {
      state.token = ''
      state.account = ''
      localStorage.clear()
    },
    login (state, data) {
      localStorage.setItem('token', data.token)
      localStorage.setItem('account', data.account)
      state.token = data.token
      state.account = data.account
    },
    refreshUser (state) {
      state.token = localStorage.token
      state.account = localStorage.account
    }
  }
}

export default user
