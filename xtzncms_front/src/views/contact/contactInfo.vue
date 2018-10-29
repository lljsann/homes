<style lang='less'>
    @import './contactInfo.less';
</style>

<template>
  <div>
    <Card>
        <p slot='title'>
            联系方式
        </p>
        <div>
          <Form ref='xtForm' :model='xtForm' :label-width='100' label-position='right'>
              <FormItem v-show='false'>
                <Input v-model='xtForm.uid' ></Input>
              </FormItem>
              <FormItem label='公司名称：'>
                <Input v-model='xtForm.name' placeholder="请输入公司全称" style="width: 400px;">{{ xtForm.name }}</Input>
              </FormItem>
              <FormItem label='公司LOGO：'>
                <Avatar size='large' :src='xtForm.logo' />
              </FormItem>
              <FormItem label='LOGO上传：'>
                <Upload :action='logoAction' :headers='upload.headers' :data='upload.data' name='file' :on-success='uploadSuccess' :show-upload-list='false' :format='["jpg","jpeg","png"]' :on-format-error='uploadFormatError'
                  :before-upload='uploadBefore'>
                  <Button type='ghost' icon='ios-cloud-upload-outline'>点击上传</Button>
                </Upload>
              </FormItem>
              <FormItem label='二维码：'>
                <Avatar size='large' :src='xtForm.qrCode' />
              </FormItem>
              <FormItem label='二维码上传：'>
                <Upload :action='qrCodeAction' :headers='upload.headers' :data='upload.data' name='file' :on-success='uploadOk' :show-upload-list='false' :format='["jpg","jpeg","png"]' :on-format-error='uploadFormatError'
                  :before-upload='uploadBefore'>
                  <Button type='ghost' icon='ios-cloud-upload-outline'>点击上传</Button>
                </Upload>
              </FormItem>
              <FormItem label='地址：'>
                <Input v-model='xtForm.address' placeholder="请输入公司地址" style="width: 400px;">{{ xtForm.address }}</Input>
              </FormItem>
              <FormItem label='电话：'>
                <Input v-model='xtForm.phone' placeholder="请输入公司电话" style="width: 400px;">{{ xtForm.phone }}</Input>
              </FormItem>
              <FormItem label='传真：'>
                <Input v-model='xtForm.fax' placeholder="请输入公司传真" style="width: 400px;">{{ xtForm.fax }}</Input>
              </FormItem>
              <FormItem label='邮编：'>
                <Input v-model='xtForm.postCode' placeholder="请输入公司邮编" style="width: 400px;">{{ xtForm.postCode }}</Input>
              </FormItem>
              <FormItem label='邮箱：'>
                <Input v-model='xtForm.email' placeholder="请输入公司邮箱" style="width: 400px;">{{ xtForm.email }}</Input>
              </FormItem>
              <div>
                <Button type='primary' style='width: 100px;' :loading='save_loading' @click='saveEdit'>保存</Button>
              </div>
          </Form>
        </div>
    </Card>
  </div>
</template>

<script>
export default {
  name: 'contact',
  data () {
    return {
      xtForm: {
        uid: '',
        name: '',
        logo: '',
        qrCode: '',
        address: '',
        phone: '',
        fax: '',
        postCode: '',
        email: ''
      },
      url: '/usr/java/xtzncms/docs/images/',
      upload: {
        headers: {},
        data: {}
      },
      logoAction: this.$global.serverPath + 'communication/uploadLogo',
      qrCodeAction: this.$global.serverPath + 'communication/uploadQrCode',
      save_loading: false
    }
  },
  mounted () {
    this.initContact()
  },
  methods: {
    initContact () {
      this.refreshContactData()
    },
    refreshContactData () {
      this.$api.get('communication/mine', null, r => {
        console.log(r.data)
        if (r.rspCode === 800) {
          this.xtForm.uid = r.data[0].uid
          this.xtForm.name = r.data[0].name
          this.xtForm.logo = this.$global.headerPath + r.data[0].logo
          this.xtForm.qrCode = this.$global.headerPath + r.data[0].qrCode
          console.log(this.xtForm.qrCode)
          this.xtForm.address = r.data[0].address
          this.xtForm.phone = r.data[0].phone
          this.xtForm.fax = r.data[0].fax
          this.xtForm.postCode = r.data[0].postCode
          this.xtForm.email = r.data[0].email
        } else {
          this.$Message.error(r.msg)
        }
      }, () => {
      })
    },
    uploadBefore (file) {
    },
    uploadSuccess (response, file, fileList) {
      this.$Message.success('上传LOGO成功')
      this.initContact()
    },
    uploadOk (response, file, fileList) {
      this.$Message.success('上传二维码成功')
      this.initContact()
    },
    uploadFormatError (file, fileList) {
      this.$Message.error('请选择有效的图片文件')
    },
    saveEdit () {
      this.save_loading = true
      this.$api.put('communication', this.xtForm, r => {
        if (r.rspCode === 800) {
          this.$Message.success('更新信息成功')
          this.save_loading = false
          this.refreshContactData()
        } else {
          this.$Message.error(r.msg)
          this.save_loading = false
        }
      }, () => {
      })
    }
  }
}
</script>
