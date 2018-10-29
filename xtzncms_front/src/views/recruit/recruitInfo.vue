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
      <Button type="primary" icon="plus-round" @click='insertBtn' class="btnGp">发布招聘</Button>
           <Button type='primary' icon='refresh' @click='refresh' class='btnGp'>刷新</Button>
      <Input v-model='searchValue' icon='ios-search' placeholder='输入查询条件' style='width: 400px;float:right;padding:5px 0px;' @on-click='search'>
              <Select  v-model='searchType' slot='prepend' style='width: 80px;'>
                  <Option value='name'>岗位名称</Option>
                  <Option value='address'>任职地点</Option>
              </Select>
            </Input>
            <Table ref='typeTable' :loading='loading' :columns='tableColumns' :data='tableData' border disabled-hover @on-selection-change='selectTable'></Table>
      <div style='float: right;margin: 10px;overflow: hidden'>
        <Page  :total='total' :current='current' @on-change='cpage' :page-size='pageSize'></Page>
      </div>
      <Modal v-model='modalFlag' :title='modalTitle' @on-visible-change='ovc' width='750'>
        <Form ref='jobForm' :model='formItem' class='f' :label-width='80' :rules='ruleValidate' >
          <FormItem label='岗位名称' prop='name'>
              <Input v-model='formItem.name' placeholder='请填写岗位名称' :disabled='modalType==3' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='任职地点' prop='address'>
              <Input v-model='formItem.address' placeholder='请填写任职地点' :readonly='modalType==2'></Input>
          </FormItem>
          <FormItem label='岗位职责' prop='duty'>
              <Input v-model='formItem.duty' placeholder='请填写岗位职责' :readonly='modalType==2' type="textarea" :rows="5"></Input>
          </FormItem>
          <FormItem label='任职要求' prop='demand'>
              <Input v-model='formItem.demand' placeholder='请填写任职要求' :readonly='modalType==2' type="textarea" :rows="5"></Input>
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
const limit = 3
export default {
  data () {
    return {
      baseUrl: this.$global.serverPath,
      modalFlag: false,
      modalType: 0,
      modalTitle: '',
      formItem: {
        name: '',
        address: '',
        duty: '',
        demand: ''
      },
      searchType: 'name',
      searchValue: '',
      ruleValidate: {
        name: [
          {required: true, message: '岗位名称不能为空', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '任职地点不能为空', trigger: 'blur'}
        ],
        duty: [
          {required: true, message: '岗位职责不能为空', trigger: 'blur'}
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
          title: '岗位名称',
          key: 'name',
          width: 250
        },
        {
          title: '岗位职责',
          key: 'duty'
        },
        {
          title: '任职要求',
          key: 'demand'
        },
        {
          title: '任职地点',
          key: 'address',
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
      this.$api.get('job/page?page=' + page + '&limit=' + limit + '&searchType=' + this.searchType + '&searchValue=' + this.searchValue, null, r => {
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
      this.modalTitle = '发布招聘'
      this.modalType = 1
      this.modalFlag = true
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
      this.$refs['jobForm'].validate(fg => {
        if (fg) {
          this.loadingStatus = true
          let param = new FormData()
          param.append('name', this.formItem.name)
          param.append('address', this.formItem.address)
          param.append('duty', this.formItem.duty)
          param.append('demand', this.formItem.demand)
          let config = {
            headers: {
              'Content-Type': 'multipart/form-data',
              'token': this.$store.state.user.token ? this.$store.state.user.token : null
            }
          }
          axios.post(this.baseUrl + 'job', param, config)
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
      this.formItem = {
        name: '',
        address: '',
        duty: '',
        demand: ''
      }
      this.$refs['jobForm'].resetFields()
    },
    delete (row) {
      this.$api.delete('job?uid=' + row.uid, null, r => {
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
