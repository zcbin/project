<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">申请</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column type="index" width="50" />
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="errorCode" label="错误码" />
      <el-table-column label="描述" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.desc }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="applicantId" label="申请人ID" />
      <el-table-column prop="addTime" label="申请时间" width="160" />
      <el-table-column prop="approval" label="审批状态" :formatter="approvalFormatter" />
      <el-table-column prop="approverId" label="审批人ID" />
      <el-table-column prop="approvalTime" label="审批时间" width="160" />
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.approval=='0'" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-if="scope.row.approval=='0'" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
          <el-tag v-if="scope.row.approval=='1'" type="success">已同意</el-tag>
          <el-tag v-if="scope.row.approval=='2'" type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="错误码" prop="errorCode">
          <el-input v-model="dataForm.errorCode"/>
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="dataForm.desc"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="pr imary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { insert, update, deleteData, getListByUser } from '@/api/errorcode'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        listQuery: {
          id: undefined,
          page: 1,
          limit: 20,
          errorCode: undefined
        },
        list: null,
        listLoading: true,
        dataForm: {
          approval: '',
          id: undefined,
          errorCode: '',
          desc: ''
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '申请'
        },
        rules: {
          errorCode: [
            { required: true, message: '错误码不能为空', trigger: 'blur' }
          ],
          desc: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getListByUser(this.listQuery).then(response => {
          this.list = response.data
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchData()
      },
      resetForm() {
        this.dataForm = {
          approval: '0',
          id: undefined,
          errorCode: '',
          desc: ''
        }
      },
      handleCreate() {
        this.resetForm()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            insert(this.dataForm).then(response => {
                this.list.unshift(response.data)
                this.dialogFormVisible = false
                this.$notify.success({
                  title: '成功',
                  message: '创建成功'
                })
              })
          }
        })
      },
      handleUpdate(row) {
        this.dataForm = Object.assign({}, row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            update(this.dataForm).then(() => {
                for (const v of this.list) {
                  if (v.id === this.dataForm.id) {
                    const index = this.list.indexOf(v)
                    this.list.splice(index, 1, this.dataForm)
                    break
                  }
                }
                this.dialogFormVisible = false
                this.$notify.success({
                  title: '成功',
                  message: '更新成功'
                })
              })
          }
        })
      },
      handleDelete(row) {
        deleteData(row).then(response => {
            this.$notify.success({
              title: '成功',
              message: '删除成功'
            })
            const index = this.list.indexOf(row)
            this.list.splice(index, 1)
          })
      },
      approvalFormatter(row, column) {
        if (row.approval === '1') {
          return '审批通过'
        } else if (row.approval === '2') {
          return '审批未通过'
        } else {
          return '未审批'
        }
      }
    }
  }
</script>
