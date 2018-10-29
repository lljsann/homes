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
      <Button type="primary" icon="plus-round" @click='insertBtn' class="btnGp">发布产品</Button>
           <Button type='primary' icon='refresh' @click='refresh' class='btnGp'>刷新</Button>
      <Input v-model='searchValue' icon='ios-search' placeholder='输入查询条件' style='width: 400px;float:right;padding:5px 0px;' @on-click='search'>
              <Select  v-model='searchType' slot='prepend' style='width: 80px;'>
                  <Option value='name'>名称</Option>
                  <Option value='synopsis'>简介</Option>
              </Select>
            </Input>
            <Table ref='typeTable' :loading='loading' :columns='tableColumns' :data='tableData' border disabled-hover @on-selection-change='selectTable'></Table>
      <div style='float: right;margin: 10px;overflow: hidden'>
        <Page  :total='total' :current='current' @on-change='cpage' :page-size='pageSize'></Page>
      </div>
      <Modal v-model='modalFlag' :title='modalTitle' @on-visible-change='ovc'>
        <Form ref='governmentForm' :model='formItem' class='f' :label-width='80' :rules='ruleValidate' >
          <FormItem label='产品名称' prop='name'>
              <Input v-model='formItem.name' placeholder='请填写产品名称' :disabled='modalType==3' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='产品简介' prop='synopsis'>
              <Input v-model='formItem.synopsis' placeholder='请填写产品简介' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='产品详情' prop='content'>
              <Input v-model='formItem.content' placeholder='请填写产品详情' :readonly='modalType==2' type="textarea" :rows="4"></Input>
          </FormItem>
          <!-- <FormItem label='产品优势' prop='virtue'>
              <Input v-model='formItem.virtue' placeholder='请填写产品优势' :readonly='modalType==2'></Input>
          </FormItem> -->
          <FormItem label='展示图名称' prop='imageName'>
              <Input v-model='formItem.imageName' placeholder='请填写展示图名称' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='产品链接' prop='url'>
              <Input v-model='formItem.url' placeholder='请填写产品链接' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='产品图' class='margin-bottom-5'>
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
        synopsis: '',
        content: '',
        // virtue: '',
        imageName: '',
        url: '',
        fileList: []
      },
      searchType: 'name',
      searchValue: '',
      ruleValidate: {
        name: [
          {required: true, message: '产品名称不能为空', trigger: 'blur'}
        ],
        synopsis: [
          {required: true, message: '产品简介不能为空', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '产品详情不能为空', trigger: 'blur'}
        ],
        // virtue: [
        //   {required: true, message: '产品优势不能为空', trigger: 'blur'}
        // ],
        url: [
          {required: true, message: '产品链接不能为空', trigger: 'blur'}
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
          title: '产品名称',
          key: 'name',
          width: 200
        },
        {
          title: '产品简介',
          key: 'synopsis'
        },
        {
          title: '产品详情',
          key: 'content',
          width: 300,
          align: 'center'
        },
        // {
        //   title: '产品优势',
        //   key: 'virtue',
        //   width: 150,
        //   align: 'center'
        // },
        {
          title: '展示图名称',
          key: 'imageName',
          width: 200,
          align: 'center'
        },
        {
          title: '产品链接',
          key: 'url',
          width: 150,
          align: 'center'
        },
        {
          title: '发布时间',
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
      this.$api.get('government/page?page=' + page + '&limit=' + limit + '&searchType=' + this.searchType + '&searchValue=' + this.searchValue, null, r => {
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
      this.modalTitle = '添加产品'
      this.modalType = 1
      this.modalFlag = true
    },
    handleMultipleUpload (file) {
      this.formItem.fileList.push(file)
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
      this.$refs['governmentForm'].validate(fg => {
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
            param.append('synopsis', this.formItem.synopsis)
            param.append('content', this.formItem.content)
            // param.append('virtue', this.formItem.virtue)
            param.append('imageName', this.formItem)
            param.append('url', this.formItem.url)
            let config = {
              headers: {
                'Content-Type': 'multipart/form-data',
                'token': this.$store.state.user.token ? this.$store.state.user.token : null
              }
            }
            axios.post(this.baseUrl + 'government', param, config)
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
        synopsis: '',
        content: '',
        // virtue: '',
        imageName: '',
        url: '',
        fileList: []
      }
      this.$refs['governmentForm'].resetFields()
    },
    delete (row) {
      this.$api.delete('government?uid=' + row.uid, null, r => {
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
