<style scoped>
.btnGp{
    margin: 5px 0px;
}
.f .ivu-btn-ghost{
  background-color: #5cadff;
  color:white;
}
</style>
<template>
<div>
      <Button type="primary" icon="plus-round" @click='insertBtn' class="btnGp">添加资质</Button>
           <Button type='primary' icon='refresh' @click='refresh' class='btnGp'>刷新</Button>
      <Input v-model='searchValue' icon='ios-search' placeholder='输入查询条件' style='width: 400px;float:right;padding:5px 0px;' @on-click='search'>
              <Select  v-model='searchType' slot='prepend' style='width: 80px;'>
                  <Option value='title'>标题</Option>
                  <Option value='detail'>详情</Option>
                  <Option value='type'>类型</Option>
              </Select>
            </Input>
            <Table ref='typeTable' :loading='loading' :columns='tableColumns' :data='tableData' border disabled-hover @on-selection-change='selectTable'></Table>
      <div style='float: right;margin: 10px;overflow: hidden'>
        <Page  :total='total' :current='current' @on-change='cpage' :page-size='pageSize'></Page>
      </div>
      <Modal v-model='modalFlag' :title='modalTitle' @on-visible-change='ovc'>
        <Form ref='honorForm' :model='formItem' class='f' :label-width='80' :rules='ruleValidate' >
          <FormItem label='资质标题' prop='title'>
              <Input v-model='formItem.title' placeholder='请填写资质标题' :disabled='modalType==3' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='资质详情' prop='detail'>
              <Input v-model='formItem.detail' placeholder='请填写资质详情' :readonly='modalType==2' type="textarea" :rows="3"></Input>
          </FormItem>
          <FormItem label='资质类型' prop='type'>
              <Input v-model='formItem.type' placeholder='请填写资质类型' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label="图片" v-show='modalType==1'>
            <Upload :before-upload="handleUpload" action="">
              <Button type="ghost" icon="ios-cloud-upload-outline">上传</Button>
            </Upload>
            <div v-if="file !== null">{{ file.name }}</div>
          </FormItem>
          </Form>
          <div slot='footer'>
          <Button type='primary' :loading='loadingStatus' v-show='modalType==1' @click='okMed' >确认</Button>
          <Button type='ghost' style='margin-left: 8px' @click='cancelMed'>取消</Button>
        </div>
      </Modal>
</div>
</template>
<script>
import axios from 'axios'
const limit = 10
export default {
  data () {
    return {
      baseUrl: this.$global.serverPath,
      modalFlag: false,
      modalType: 0,
      modalTitle: '',
      formItem: {
        title: '',
        detail: '',
        type: ''
      },
      searchType: 'title',
      searchValue: '',
      file: null,
      ruleValidate: {
        title: [
          {required: true, message: '资质标题不能为空', trigger: 'blur'}
        ],
        detail: [
          {required: true, message: '资质详情不能为空', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '资质类型不能为空', trigger: 'blur'}
        ]
      },
      loading: false,
      loadingStatus: false,
      total: 0,
      current: 1,
      sels: [],
      pageSize: limit,
      tableData: [],
      tableColumns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          title: '资质标题',
          key: 'title',
          width: 250
        },
        {
          title: '资质详情',
          key: 'detail'
        },
        {
          title: '资质类型',
          key: 'type',
          width: 100,
          align: 'center'
        },
        {
          title: '添加时间',
          key: 'createTime',
          width: 100,
          align: 'center'
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          width: 200,
          render: (h, params) => {
            let arr = []
            arr.push(h('Button', {
              props: {
                type: 'error',
                size: 'small'
              },
              style: {
                marginRight: '5px'
              },
              on: {
                click: () => {
                  this.delete(params.row)
                }
              }
            }, '删除'))
            return h('div', arr)
          }
        }
      ]
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    cpage (page) {
      this.getData(page, limit)
    },
    selectTable (ses) {
      this.sels = ses
    },
    getData (page, limit) {
      this.loading = true
      this.$api.get('companyaptitude/page?page=' + page + '&limit=' + limit + '&searchType=' + this.searchType + '&searchValue=' + this.searchValue, null, r => {
        if (r.rspCode === 800) {
          console.log(r.data.data)
          this.tableData = r.data.data
          this.total = r.data.total
          this.current = r.data.nowPage === 0 ? 1 : r.data.nowPage
        } else {
          this.$Message.error(r.msg)
        }
        this.loading = false
      }, () => {
        this.$Message.error('异常')
        this.loading = false
      })
    },
    refresh () {
      this.searchType = 'title'
      this.searchValue = ''
      this.getData(1, limit)
    },
    insertBtn () {
      this.modalTitle = '添加资质'
      this.modalType = 1
      this.modalFlag = true
    },
    handleUpload (file) {
      this.file = file
      return false
    },
    search () {
      if (this.searchType === '') {
        this.$Message.error('请选择查询条件')
      } else if (this.searchValue === '') {
        this.$Message.error('请填写查询关键字')
      } else {
        this.getData(1, limit)
      }
    },
    okMed () {
      this.$refs['honorForm'].validate(fg => {
        if (fg) {
          return new Promise((resolve, reject) => {
            if (this.file == null) {
            //  reject('图片必选')
            } else {
              resolve('success')
            }
          }).then(r => {
            this.loadingStatus = true
            let param = new FormData()
            param.append('file', this.file)
            param.append('title', this.formItem.title)
            param.append('detail', this.formItem.detail)
            param.append('type', this.formItem.type)
            let config = {
              headers: {
                'Content-Type': 'multipart/form-data',
                'token': this.$store.state.user.token ? this.$store.state.user.token : null
              }
            }
            axios.post(this.baseUrl + 'companyaptitude', param, config)
              .then(r => {
                this.loadingStatus = false
                if (r.data.rspCode === 800) {
                  this.$Message.success('录入数据成功')
                  this.refresh()
                  this.cancelMed()
                } else {
                  this.$Message.error(r.data.msg)
                }
              })
          }).catch(err => {
            this.$Message.error(err)
          })
        }
      })
    },
    ovc (flag) {
      if (!flag) { this.cancelMed() }
    },
    cancelMed () {
      this.modalTitle = ' '
      this.modalType = 0
      this.modalFlag = false
      this.formInit()
    },
    formInit () {
      this.file = null
      this.formItem = {
        title: '',
        detail: '',
        type: ''
      }
      this.$refs['honorForm'].resetFields()
    },
    delete (row) {
      this.$api.delete('companyaptitude?uid=' + row.uid, null, r => {
        if (r.rspCode === 800) {
          this.$Message.success('删除成功')
          this.cancelMed()
          this.refresh()
        }
      }, () => {
        this.$Message.error('异常')
      })
    }
  }
}

</script>
