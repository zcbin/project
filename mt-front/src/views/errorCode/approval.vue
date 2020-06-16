<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
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
          <el-button v-if="scope.row.approval=='0'" type="primary" size="mini" @click="handleUpdate(scope.row, 1)">同意</el-button>
          <el-button v-if="scope.row.approval=='0'" type="danger" size="mini" @click="handleUpdate(scope.row, 2)">拒绝</el-button>
          <el-tag v-if="scope.row.approval=='1'" type="success">已同意</el-tag>
          <el-tag v-if="scope.row.approval=='2'" type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import { getList, approvalUpdate } from '@/api/errorcode'

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
          page: 1,
          limit: 20,
          errorCode: undefined
        },
        list: null,
        listLoading: true
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getList(this.listQuery).then(response => {
          this.list = response.data
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.fetchData()
      },
      handleUpdate(row, approval) {
        row.approval = approval
        approvalUpdate(row).then(() => {
          for (const v of this.list) {
            if (v.id === row.id) {
              const index = this.list.indexOf(v)
              this.list.splice(index, 1, row)
              break
            }
          }
          this.dialogFormVisible = false
          this.$notify.success({
            title: '成功',
            message: '更新成功'
          })
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
