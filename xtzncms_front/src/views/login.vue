<style lang="less">
    @import './login.less';
</style>
<template>
  <div  class="login"  @keydown.enter="handleSubmit">
    <div style="padding-top:90px;padding-left:70px">
      <img  src="./../images/logo.png" style="float:left"/>
      <img  src="./../images/title.svg" style="float:left;margin-top:0px;margin-left:20px"/>
    </div>
    <div class="login-con">
      <img src='../images/home-img.png' class='img' width="40%">
      <Card :bordered="false" style="width:300px;float:right">
        <p slot="title">
          <Icon type="log-in"></Icon>
            欢迎登录
        </p>
        <div class="form-con">
          <Form ref="loginForm" :model="form" :rules="rules">
            <FormItem prop="userName">
              <Input v-model="form.userName" placeholder="请输入用户名" clearable >
                <span slot="prepend">
                  <Icon :size="16" type="person"></Icon>
                </span>
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input type="password" v-model="form.password" placeholder="请输入密码">
                <span slot="prepend">
                  <Icon :size="14" type="locked"></Icon>
                </span>
              </Input>
            </FormItem>
            <FormItem class="margin-bottom-5">
              <Checkbox  v-model="form.remenberMe" >记住我（7天内免登陆）</Checkbox>
            </FormItem>
            <FormItem>
              <Button @click="handleSubmit" type="primary" long :loading='form.ldstatus'>登录</Button>
            </FormItem>
          </Form>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  data () {
    return {
      form: {
        userName: '',
        password: '',
        remenberMe: false,
        ldstatus: false
      },
      rules: {
        userName: [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    let acc = Cookies.get('account')
    this.form.userName = acc == null ? '' : acc
    var pa = this.$route.query
    if (pa.code === 801) {
      this.$Message.error({
        content: '未认证',
        duration: 4
      })
    }
    if (pa.code === 807) {
      this.$Message.error({
        content: '登录已失效,请重新登录',
        duration: 4
      })
    }
    if (this.$store.state.user.token) {
      this.$router.push({
        name: 'home_index'
      })
    }
  },

  methods: {
    handleSubmit () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.form.ldstatus = true
          this.$api.post('login/pclogin', this.form, rp => {
            this.form.ldstatus = false
            if (rp.rspCode === 800) {
              Cookies.set('account', rp.data.account)
              this.$store.commit('login', {
                token: rp.data.token,
                account: rp.data.account,
                headUrl: rp.data.headUrl
              })
              this.$store.commit('updateMenulist')
              this.$router.push({
                name: 'home_index'
              })
            } else {
              this.$Message.error(rp.msg)
            }
          }, err => {
            console.log(err)
          }
          )
        }
      })
    }
  }
}
</script>
