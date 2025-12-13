<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="注册邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入注册邮箱"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable style="width: 150px">
          <el-option label="不限" :value="null" />
          <el-option label="待审核" :value="0" />
          <el-option label="已通过" :value="1" />
          <el-option label="已拒绝" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="注册时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeCreatedAt"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Select"
          :disabled="multiple || queryParams.status === 1 || queryParams.status === 2"
          @click="handleBatchApprove"
          v-hasPermi="['recruit:company:audit:approve']"
        >批量通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Close"
          :disabled="multiple || queryParams.status === 1 || queryParams.status === 2"
          @click="handleBatchReject"
          v-hasPermi="['recruit:company:audit:reject']"
        >批量拒绝</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:company:audit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="auditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" width="100" />
      <el-table-column label="注册邮箱" align="center" prop="email" min-width="250" show-overflow-tooltip />
      <el-table-column label="公司名称" align="center" prop="companyName" min-width="220" show-overflow-tooltip />
      <el-table-column label="用户角色" align="center" prop="role" width="110">
        <template #default="scope">
          <el-tag v-if="scope.row.role === 1" type="info">学生</el-tag>
          <el-tag v-else-if="scope.row.role === 2" type="primary">企业HR</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="status" width="110">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 0" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="success">已通过</el-tag>
          <el-tag v-else-if="scope.row.status === 2" type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="注册时间" align="center" prop="createdAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="220" fixed="right">
        <template #default="scope">
          <el-button 
            link 
            type="primary" 
            icon="View" 
            @click="handleView(scope.row)"
            v-hasPermi="['recruit:company:audit:query']"
          >查看详情</el-button>
          <el-button 
            link 
            type="success" 
            icon="Select" 
            @click="handleApprove(scope.row)"
            v-if="scope.row.status === 0"
            v-hasPermi="['recruit:company:audit:approve']"
          >通过</el-button>
          <el-button 
            link 
            type="danger" 
            icon="Close" 
            @click="handleReject(scope.row)"
            v-if="scope.row.status === 0"
            v-hasPermi="['recruit:company:audit:reject']"
          >拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 企业账号详情对话框 -->
    <el-dialog title="企业账号审核详情" v-model="detailOpen" width="700px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="用户ID">{{ detailData.userId }}</el-descriptions-item>
        <el-descriptions-item label="用户角色">
          <el-tag v-if="detailData.role === 2" type="primary">企业HR</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="注册邮箱" :span="2">{{ detailData.email }}</el-descriptions-item>
        <el-descriptions-item label="公司名称" :span="2">{{ detailData.companyName || '未填写' }}</el-descriptions-item>
        <el-descriptions-item label="注册时间" :span="2">{{ parseTime(detailData.createdAt) }}</el-descriptions-item>
        <el-descriptions-item label="审核状态" :span="2">
          <el-tag v-if="detailData.status === 0" type="warning">待审核</el-tag>
          <el-tag v-else-if="detailData.status === 1" type="success">已通过</el-tag>
          <el-tag v-else-if="detailData.status === 2" type="danger">已拒绝</el-tag>
        </el-descriptions-item>
        <!-- <el-descriptions-item label="审核时间" :span="2" v-if="detailData.status !== 0">
          {{ parseTime(detailData.updatedAt) }}
        </el-descriptions-item> -->
        <el-descriptions-item label="审核备注" :span="2" v-if="detailData.auditRemark">
          <div style="white-space: pre-wrap; color: #E6A23C;">{{ detailData.auditRemark }}</div>
        </el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="success" @click="handleApprove(detailData)" v-if="detailData.status === 0">通过审核</el-button>
          <el-button type="danger" @click="handleReject(detailData)" v-if="detailData.status === 0">拒绝审核</el-button>
          <el-button @click="detailOpen = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup name="CompanyAudit">
import { listCompanyAudit, getCompanyAudit, approveCompany, rejectCompany, batchApproveCompanies, batchRejectCompanies } from "@/api/recruit/audit"

const { proxy } = getCurrentInstance()

const auditList = ref([])
const detailOpen = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const multiple = ref(true)
const total = ref(0)
const daterangeCreatedAt = ref([])
const detailData = ref({})

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    email: null,
    companyName: null,
    status: null,
    createdAt: null
  }
})

const { queryParams } = toRefs(data)

function getList() {
  loading.value = true
  // 构建查询参数，与导出功能保持一致的格式
  const params = {
    pageNum: queryParams.value.pageNum,
    pageSize: queryParams.value.pageSize,
    email: queryParams.value.email,
    status: queryParams.value.status,
    params: {}
  }
  // 将 companyName 和日期范围放在 params 对象中
  if (queryParams.value.companyName) {
    params.params.companyName = queryParams.value.companyName
  }
  if (null != daterangeCreatedAt.value && '' != daterangeCreatedAt.value) {
    params.params.beginCreatedAt = daterangeCreatedAt.value[0]
    params.params.endCreatedAt = daterangeCreatedAt.value[1]
  }
  listCompanyAudit(params).then(response => {
    auditList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

function resetQuery() {
  daterangeCreatedAt.value = []
  proxy.resetForm("queryRef")
  handleQuery()
}

function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId)
  multiple.value = !selection.length
}

function handleView(row) {
  const userId = row.userId
  getCompanyAudit(userId).then(response => {
    detailData.value = response.data
    detailOpen.value = true
  })
}

function handleApprove(row) {
  const userId = row.userId
  proxy.$modal.confirm('是否确认通过用户ID为"' + userId + '"的企业账号注册审核？').then(function() {
    return approveCompany(userId)
  }).then(() => {
    getList()
    detailOpen.value = false
    proxy.$modal.msgSuccess("审核通过成功")
  }).catch(() => {})
}

function handleReject(row) {
  const userId = row.userId
  proxy.$modal.confirm('是否确认拒绝用户ID为"' + userId + '"的企业账号注册审核？').then(function() {
    return rejectCompany(userId)
  }).then(() => {
    getList()
    detailOpen.value = false
    proxy.$modal.msgSuccess("审核拒绝成功")
  }).catch(() => {})
}

function handleBatchApprove() {
  const userIds = ids.value.join(',')
  proxy.$modal.confirm('是否确认批量通过已选择企业的注册审核？').then(function() {
    return batchApproveCompanies(userIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("批量审核通过成功")
  }).catch(() => {})
}

function handleBatchReject() {
  const userIds = ids.value.join(',')
  proxy.$modal.confirm('是否确认批量拒绝已选择企业的注册审核？').then(function() {
    return batchRejectCompanies(userIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("批量审核拒绝成功")
  }).catch(() => {})
}

function handleExport() {
  const exportParams = {
    pageNum: queryParams.value.pageNum,
    pageSize: queryParams.value.pageSize,
    email: queryParams.value.email,
    status: queryParams.value.status,
    params: {}
  }
  // 将companyName和日期范围放在params对象中
  if (queryParams.value.companyName) {
    exportParams.params.companyName = queryParams.value.companyName
  }
  if (null != daterangeCreatedAt.value && '' != daterangeCreatedAt.value) {
    exportParams.params.beginCreatedAt = daterangeCreatedAt.value[0]
    exportParams.params.endCreatedAt = daterangeCreatedAt.value[1]
  }
  proxy.download('recruit/audit/company/export', exportParams, `企业审核_${new Date().getTime()}.xlsx`, {
    headers: { 'Content-Type': 'application/json' },
    transformRequest: [data => JSON.stringify(data)]
  })
}

getList()
</script>

<style scoped>
.el-descriptions :deep(.el-descriptions__label) {
  font-weight: bold;
}
</style>
