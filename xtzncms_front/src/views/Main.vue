<style>
@import 'quill/dist/quill.core.css';
@import 'quill/dist/quill.snow.css';
@import 'quill/dist/quill.bubble.css';
</style>
<style lang="less">
    @import "./main.less";
</style>
<template>
    <div class="main" :class="{'main-hide-text': shrink}">
        <div class="sidebar-menu-con" :style="{width: shrink?'60px':'200px', overflow: shrink ? 'visible' : 'auto'}">
            <shrinkable-menu
                :shrink="shrink"
                :menu-list="menuList">
                <a slot="top" class="logo-con" @click="toHome" style="display:block">
                    <img v-show="!shrink"  src="../images/logo.png" key="max-logo" />
                    <img v-show="shrink" src="../images/logo-min.png" key="min-logo" />
                </a>
            </shrinkable-menu>
        </div>
        <div class="main-header-con" :style="{paddingLeft: shrink?'60px':'200px'}">
            <div class="main-header">
                <div class="navicon-con">
                    <Button :style="{transform: 'rotateZ(' + (this.shrink ? '-90' : '0') + 'deg)'}" type="text" @click="toggleClick">
                        <Icon type="navicon" size="32"></Icon>
                    </Button>
                </div>
                <div class="header-middle-con">
                    <div class="main-breadcrumb">
                         <breadcrumb-nav :currentPath="currentPath"></breadcrumb-nav>
                    </div>
                </div>
                <div class="header-avator-con">
                    <div class="user-dropdown-menu-con">
                        <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
                            <Dropdown transfer  @on-click="handleClickUserDropdown" style="margin-left:20px;">
                                <a href="javascript:void(0)" class="iclist">
                                    <span class="main-user-name">{{ userName }}</span>
                                    <Icon type="arrow-down-b"></Icon>
                                </a>
                                <DropdownMenu slot="list">
                                    <DropdownItem name="center">修改密码</DropdownItem>
                                    <DropdownItem name="loginout" divided>退出登录</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </Row>
                    </div>
                </div>
            </div>
        </div>
        <div class="single-page-con" :style="{left: shrink?'60px':'200px'}">
            <div class="single-page">
                <router-view></router-view>
            </div>
        </div>
        <!--修改密码-->
        <Modal v-model='editPasswordModal' :closable='false' :mask-closable=false :width='500'>
            <h3 slot='header' style='color:#2D8CF0'>修改密码</h3>
            <Form ref='editPasswordForm' :model='editPasswordForm' :label-width='100' label-position='right' :rules='passwordValidate'>
                <FormItem label='旧密码' prop='password' :error='passwordError'>
                    <Input type='password' v-model='editPasswordForm.password' placeholder='请输入现在使用的密码' ></Input>
                </FormItem>
                <FormItem label='新密码' prop='newPassword'>
                    <Input type='password' v-model='editPasswordForm.newPassword' placeholder='请输入新密码，至少6位字符' ></Input>
                </FormItem>
                <FormItem label='确认新密码' prop='rePass'>
                    <Input type='password' v-model='editPasswordForm.rePass' placeholder='请再次输入新密码' ></Input>
                </FormItem>
            </Form>
            <div slot='footer'>
                <Button type='text' @click='cancelEditPass'>取消</Button>
                <Button type='primary' :loading='savePassLoading' @click='saveEditPass'>保存</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
import shrinkableMenu from './main-components/shrinkable-menu/shrinkable-menu.vue'
import breadcrumbNav from './main-components/breadcrumb-nav.vue'

export default {
  components: {
    shrinkableMenu,
    breadcrumbNav
  },
  data () {
    const valideRePassword = (rule, value, callback) => {
      if (value !== this.editPasswordForm.newPassword) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }
    return {
      mesCount: '',
      shrink: false,
      userName: '',
      editPasswordModal: false, // 修改密码模态框显示
      savePassLoading: false,
      passwordError: '',
      editPasswordForm: {
        password: '',
        newPassword: '',
        rePass: ''
      },
      passwordValidate: {
        password: [
          { required: true, message: '请输入原密码', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, message: '请至少输入6个字符', trigger: 'blur' },
          { max: 32, message: '最多输入32个字符', trigger: 'blur' }
        ],
        rePass: [
          { required: true, message: '请再次输入新密码', trigger: 'blur' },
          { validator: valideRePassword, trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    menuList () {
      return this.$store.state.app.menuList
    },
    currentPath () {
      return this.$store.state.app.currentPath // 当前面包屑数组
    }
  },
  methods: {
    init () {
      let na = this.$store.state.user.account
      this.userName = na
      this.$Message.config({
        top: 20,
        duration: 3
      })
    },
    toggleClick () {
      this.shrink = !this.shrink
    },
    handleClickUserDropdown (name) {
      if (name === 'loginout') {
        this.$store.commit('logout')
        this.$router.push({
          name: 'login'
        })
      } else if (name === 'center') {
        this.editPasswordModal = true
      }
    },
    toHome () {
      this.$router.push('/')
    },
    cancelEditPass () {
      this.editPasswordModal = false
    },
    saveEditPass () {
      this.$refs['editPasswordForm'].validate((valid) => {
        if (valid) {
          this.savePassLoading = true
          this.$api.put('user/updatePwd', this.editPasswordForm, r => {
            if (r.rspCode === 800) {
              this.$Message.success('修改密码成功')
              this.savePassLoading = false
              this.cancelEditPass()
            } else {
              this.$Message.error(r.msg)
              this.savePassLoading = false
            }
          }, err => {
            console.log(err)
          }
          )
        }
      })
    }
  },
  watch: {
    '$route' (to) {
      this.$util.setCurrentPath(this, to.name)
    }
  },
  mounted () {
    this.init()
  }
}
</script>
