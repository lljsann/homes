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
      <Button type="primary" icon="plus-round" @click='insertBtn' class="btnGp">添加板块</Button>
           <Button type='primary' icon='refresh' @click='refresh' class='btnGp'>刷新</Button>
            <Table ref='typeTable' :loading='loading' :columns='tableColumns' :data='tableData' border disabled-hover @on-selection-change='selectTable'></Table>
      <div style='float: right;margin: 10px;overflow: hidden'>
        <Page  :total='total' :current='current' @on-change='cpage' :page-size='pageSize'></Page>
      </div>
      <Modal v-model='modalFlag' :title='modalTitle' @on-visible-change='ovc'>
        <Form ref='workForm' :model='formItem' class='f' :label-width='80' :rules='ruleValidate' >
          <FormItem label='板块名称' prop='name'>
              <Input v-model='formItem.name' placeholder='请填写板块名称' :disabled='modalType==3' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='板块简介' prop='remark'>
              <Input v-model='formItem.remark' placeholder='请填写板块简介' :readonly='modalType==2'  type="textarea" :rows="3"></Input>
          </FormItem>
          <FormItem label='添加人' prop='author'>
              <Input v-model='formItem.author' placeholder='请填写添加人' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='展示图' class='margin-bottom-5'>
              <Upload
                  multiple
                  action=""  :before-upload="handleMultipleUpload">
                  <Button type="primary" icon="ios-cloud-upload-outline">点击上传</Button>
              </Upload>
          </FormItem>
          <FormItem v-if="formItem.fileList.length !== 0">
              <div v-for='(item,index) in formItem.fileList' :key='index'>
                {{item.name}}
              </div>
          </FormItem>
          </Form>
          <div slot='footer'>
          <Button type='primary' :loading='loadingStatus' v-show='modalType==1' @click='okMed' >确定</Button>
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
        name: '',
        remark: '',
        author: '',
        fileList: []
      },
      ruleValidate: {
        name: [
          {required: true, message: '板块名称不能为空', trigger: 'blur'}
        ],
        remark: [
          {required: true, message: '板块简介不能为空', trigger: 'blur'}
        ],
        author: [
          {required: true, message: '添加人不能为空', trigger: 'blur'}
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
          title: '板块名称',
          key: 'name',
          width: 200
        },
        {
          title: '板块简介',
          key: 'remark'
        },
        {
          title: '添加人',
          key: 'author',
          width: 150,
          align: 'center'
        },
        {
          title: '添加时间',
          key: 'createTime',
          width: 150,
          align: 'center'
        },
        {
          title: '操作',
          key: 'action',
          align: 'center',
          width: 120,
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
      this.$api.get('work/page?page=' + page + '&limit=' + limit, null, r => {
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
      this.searchType = 'name'
      this.searchValue = ''
      this.getData(1, limit)
    },
    insertBtn () {
      this.modalTitle = '添加板块'
      this.modalType = 1
      this.modalFlag = true
    },
    handleMultipleUpload (file) {
      this.formItem.fileList.push(file)
      return false
    },
    okMed () {
      this.$refs['workForm'].validate(fg => {
        if (fg) {
          return new Promise((resolve, reject) => {
            if (this.formItem.fileList.length === 0) {
            //  reject('图片必选')
            } else {
              resolve('success')
            }
          }).then(r => {
            this.loadingStatus = true
            let param = new FormData()
            for (let i = 0; i < this.formItem.fileList.length; i++) {
              param.append('file[]', this.formItem.fileList[i])
            }
            param.append('name', this.formItem.name)
            param.append('remark', this.formItem.remark)
            param.append('author', this.formItem.author)
            let config = {
              headers: {
                'Content-Type': 'multipart/form-data',
                'token': this.$store.state.user.token ? this.$store.state.user.token : null
              }
            }
            axios.post(this.baseUrl + 'work', param, config)
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
        name: '',
        remark: '',
        author: '',
        fileList: []
      }
      this.$refs['workForm'].resetFields()
    },
    delete (row) {
      this.$api.delete('work?uid=' + row.uid, null, r => {
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
