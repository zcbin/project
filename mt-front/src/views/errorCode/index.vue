<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.errorCode" clearable class="filter-item" style="width: 200px;" placeholder="请输入错误码"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-document" @click="handleDownload">导出</el-button>
    </div>
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
    </el-table>
  </div>
</template>

<script>
import { getList } from '@/api/errorcode'

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
        approval: '1', // 审批通过
        page: 1,
        limit: 20,
        errorCode: undefined
      },
      list: null,
      listLoading: true,
      downloadLoading: false
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
    approvalFormatter(row, column) {
      if (row.approval === '1') {
        return '审批通过'
      } else if (row.approval === '2') {
        return '审批未通过'
      } else {
        return '未审批'
      }
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['ID', '错误码', '描述', '申请人ID', '审批人ID', '创建时间', '审批时间']
        const filterVal = ['id', 'errorCode', 'desc', 'applicantId', 'approverId', 'addTime', 'approvalTime']
        const list = this.list
        const data = this.formatJson(filterVal, list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'errorCode',
          autoWidth: true,
          bookType: 'xlsx'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
