<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="企业名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="岗位名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入岗位名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable style="width: 150px">
          <el-option label="不限" :value="null" />
          <el-option label="待审核" :value="10" />
          <el-option label="已通过" :value="20" />
          <el-option label="已拒绝" :value="30" />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" style="width: 308px">
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
          :disabled="multiple || queryParams.status === 20 || queryParams.status === 30"
          @click="handleBatchApprove"
          v-hasPermi="['recruit:audit:approve']"
        >批量通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Close"
          :disabled="multiple || queryParams.status === 20 || queryParams.status === 30"
          @click="handleBatchReject"
          v-hasPermi="['recruit:audit:reject']"
        >批量拒绝</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:audit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="auditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="岗位ID" align="center" prop="id" width="80" />
      <el-table-column label="企业名称" align="center" prop="companyName" min-width="150" show-overflow-tooltip />
      <el-table-column label="岗位名称" align="center" prop="title" min-width="180" show-overflow-tooltip />
      <el-table-column label="工作地点" align="center" prop="workLocation" min-width="140" show-overflow-tooltip />
      <el-table-column label="岗位性质" align="center" prop="workNature" width="100">
        <template #default="scope">
          <dict-tag :options="biz_work_nature" :value="scope.row.workNature"/>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="status" width="100">
        <template #default="scope">
          <el-tag v-if="scope.row.status === 10" type="warning">待审核</el-tag>
          <el-tag v-else-if="scope.row.status === 20" type="success">已通过</el-tag>
          <el-tag v-else-if="scope.row.status === 30" type="danger">已拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="提交人邮箱" align="center" prop="postedByUserName" min-width="150" show-overflow-tooltip />
      <el-table-column label="提交时间" align="center" prop="createdAt" width="160">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" align="center" prop="auditedAt" width="160">
        <template #default="scope">
          <span>{{ parseTime(scope.row.auditedAt) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="220" fixed="right">
        <template #default="scope">
          <el-button 
            link 
            type="primary" 
            icon="View" 
            @click="handleView(scope.row)"
            v-hasPermi="['recruit:audit:query']"
          >查看详情</el-button>
          <el-button 
            link 
            type="success" 
            icon="Select" 
            @click="handleApprove(scope.row)"
            v-if="scope.row.status === 10"
            v-hasPermi="['recruit:audit:approve']"
          >通过</el-button>
          <el-button 
            link 
            type="danger" 
            icon="Close" 
            @click="handleReject(scope.row)"
            v-if="scope.row.status === 10"
            v-hasPermi="['recruit:audit:reject']"
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

    <!-- 岗位详情对话框 -->
    <el-dialog title="岗位详情" v-model="detailOpen" width="1000px" append-to-body>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="基本信息" name="basic">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="岗位ID">{{ detailData.id }}</el-descriptions-item>
            <el-descriptions-item label="企业名称">{{ detailData.companyName }}</el-descriptions-item>
            <el-descriptions-item label="岗位名称">{{ detailData.title }}</el-descriptions-item>
            <el-descriptions-item label="岗位类别">{{ detailData.typeName }}</el-descriptions-item>
            <el-descriptions-item label="所属部门">{{ detailData.department }}</el-descriptions-item>
            <el-descriptions-item label="薪资范围">
              <span v-if="detailData.minSalary === 0 && detailData.maxSalary === 0">面议</span>
              <span v-else-if="detailData.workNature === 1">{{ detailData.minSalary }}-{{ detailData.maxSalary }}</span>
              <span v-else>{{ detailData.minSalary }}-{{ detailData.maxSalary }}k</span>
            </el-descriptions-item>
            <el-descriptions-item label="工作地点" :span="2">{{ detailData.workLocation }}</el-descriptions-item>
            <el-descriptions-item label="岗位性质">
              <dict-tag :options="biz_work_nature" :value="detailData.workNature"/>
            </el-descriptions-item>
            <el-descriptions-item label="学历要求">
              <dict-tag :options="biz_required_degree_level" :value="detailData.requiredDegree"/>
            </el-descriptions-item>
            <el-descriptions-item label="招聘人数">{{ detailData.headcount }}</el-descriptions-item>
            <el-descriptions-item label="招聘截止日期">{{ detailData.deadline }}</el-descriptions-item>
            <el-descriptions-item label="岗位描述" :span="2">
              <div style="white-space: pre-wrap;">{{ detailData.description }}</div>
            </el-descriptions-item>
            <el-descriptions-item label="岗位要求" :span="2">
              <div style="white-space: pre-wrap;">{{ detailData.techRequirements }}</div>
            </el-descriptions-item>
            <el-descriptions-item label="岗位加分项" :span="2">
              <div style="white-space: pre-wrap;">{{ detailData.bonusPoints }}</div>
            </el-descriptions-item>
            <el-descriptions-item label="提交人邮箱">{{ detailData.postedByUserName }}</el-descriptions-item>
            <el-descriptions-item label="提交时间">{{ parseTime(detailData.createdAt) }}</el-descriptions-item>
            <el-descriptions-item label="当前状态" :span="2">
              <el-tag v-if="detailData.status === 10" type="warning">待审核</el-tag>
              <el-tag v-else-if="detailData.status === 20" type="success">已通过</el-tag>
              <el-tag v-else-if="detailData.status === 30" type="danger">已拒绝</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>
        
        <el-tab-pane label="审核历史" name="auditLogs">
          <el-empty v-if="!auditLogs.length" description="暂无审核记录" />
          <el-timeline v-else>
            <el-timeline-item 
              v-for="log in auditLogs" 
              :key="log.id"
              :timestamp="parseTime(log.createdAt)"
              placement="top"
              :type="log.auditStatus === 20 ? 'success' : 'danger'"
            >
              <el-card>
                <div class="audit-log-item">
                  <div class="log-header">
                    <el-tag :type="log.auditStatus === 20 ? 'success' : 'danger'" size="large">
                      {{ log.auditStatus === 20 ? '审核通过' : '审核拒绝' }}
                    </el-tag>
                  </div>
                  <el-descriptions :column="2" border class="log-details">
                    <el-descriptions-item label="审核人ID">{{ log.operatorId }}</el-descriptions-item>
                    <el-descriptions-item label="审核人联系方式">{{ log.operatorContact || '未填写' }}</el-descriptions-item>
                    <el-descriptions-item label="审核时间" :span="2">{{ parseTime(log.createdAt) }}</el-descriptions-item>
                    <el-descriptions-item label="审核备注/拒绝原因" :span="2" v-if="log.remark">
                      <div style="white-space: pre-wrap; color: #606266;">{{ log.remark }}</div>
                    </el-descriptions-item>
                    <el-descriptions-item label="审核备注" :span="2" v-else>
                      <span style="color: #909399;">无</span>
                    </el-descriptions-item>
                  </el-descriptions>
                </div>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </el-tab-pane>
      </el-tabs>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="success" @click="handleApprove(detailData)" v-if="detailData.status === 10">通过审核</el-button>
          <el-button type="danger" @click="handleReject(detailData)" v-if="detailData.status === 10">拒绝审核</el-button>
          <el-button @click="detailOpen = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 通过审核对话框 -->
    <el-dialog title="通过审核" v-model="approveOpen" width="500px" append-to-body>
      <el-form ref="approveFormRef" :model="approveForm" :rules="approveRules" label-width="100px">
        <el-form-item label="审核人联系方式" prop="operatorContact">
          <el-input 
            v-model="approveForm.operatorContact" 
            placeholder="请输入您的联系方式（必填）"
            clearable
          />
        </el-form-item>
        <el-form-item label="审核备注" prop="remark">
          <el-input 
            v-model="approveForm.remark" 
            type="textarea" 
            :rows="3"
            placeholder="请输入审核备注（可选）"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitApprove">确 定</el-button>
          <el-button @click="approveOpen = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 拒绝审核对话框 -->
    <el-dialog title="拒绝审核" v-model="rejectOpen" width="500px" append-to-body>
      <el-form ref="rejectFormRef" :model="rejectForm" :rules="rejectRules" label-width="100px">
        <el-form-item label="审核人联系方式" prop="operatorContact">
          <el-input 
            v-model="rejectForm.operatorContact" 
            placeholder="请输入您的联系方式（必填）"
            clearable
          />
        </el-form-item>
        <el-form-item label="拒绝原因" prop="remark">
          <el-input 
            v-model="rejectForm.remark" 
            type="textarea" 
            :rows="5"
            placeholder="请输入拒绝原因（必填）"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitReject">确 定</el-button>
          <el-button @click="rejectOpen = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="JobAudit">
import { listJobAudit, getJobAudit, approveJob, rejectJob, batchApproveJobs, batchRejectJobs, getJobAuditLogs } from "@/api/recruit/audit"

const { proxy } = getCurrentInstance()
const { biz_work_nature, biz_required_degree_level } = proxy.useDict('biz_work_nature', 'biz_required_degree_level')

const auditList = ref([])
const detailOpen = ref(false)
const approveOpen = ref(false)
const rejectOpen = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const multiple = ref(true)
const total = ref(0)
const daterangeCreatedAt = ref([])
const detailData = ref({})
const auditLogs = ref([])
const activeTab = ref('basic')
const approveForm = ref({
  id: null,
  operatorContact: '',
  remark: ''
})
const rejectForm = ref({
  id: null,
  operatorContact: '',
  remark: ''
})

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    companyName: null,
    title: null,
    status: null,
    createdAt: null
  },
  approveRules: {
    operatorContact: [
      { required: true, message: "审核人联系方式不能为空", trigger: "blur" }
    ]
  },
  rejectRules: {
    operatorContact: [
      { required: true, message: "审核人联系方式不能为空", trigger: "blur" }
    ],
    remark: [
      { required: true, message: "拒绝原因不能为空", trigger: "blur" },
      { min: 5, message: "拒绝原因至少5个字符", trigger: "blur" }
    ]
  }
})

const { queryParams, approveRules, rejectRules } = toRefs(data)

/** 查询岗位审核列表 */
function getList() {
  loading.value = true
  // 构建查询参数
  const params = {
    pageNum: queryParams.value.pageNum,
    pageSize: queryParams.value.pageSize,
    companyName: queryParams.value.companyName,
    title: queryParams.value.title,
    status: queryParams.value.status
  }
  // 将日期范围直接放在顶层参数中
  if (null != daterangeCreatedAt.value && '' != daterangeCreatedAt.value) {
    params.beginCreatedAt = daterangeCreatedAt.value[0]
    params.endCreatedAt = daterangeCreatedAt.value[1]
  }
  listJobAudit(params).then(response => {
    auditList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeCreatedAt.value = []
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  multiple.value = !selection.length
}

/** 查看详情 */
function handleView(row) {
  const jobId = row.id
  // 获取岗位详情
  getJobAudit(jobId).then(response => {
    const data = response.data
    // 处理字段映射：后端返回的是address_detail，前端使用workLocation
    if (data.address_detail) {
      data.workLocation = data.address_detail
    }
    detailData.value = data
    detailOpen.value = true
    activeTab.value = 'basic'
  })
  // 获取审核历史记录
  getJobAuditLogs(jobId).then(response => {
    auditLogs.value = response.data || []
  }).catch(() => {
    auditLogs.value = []
  })
}

/** 通过审核 */
function handleApprove(row) {
  approveForm.value = {
    id: row.id,
    operatorContact: '',
    remark: ''
  }
  approveOpen.value = true
}

/** 提交通过 */
function submitApprove() {
  proxy.$refs["approveFormRef"].validate(valid => {
    if (valid) {
      approveJob(approveForm.value.id, approveForm.value.operatorContact, approveForm.value.remark).then(response => {
        proxy.$modal.msgSuccess("审核通过成功")
        approveOpen.value = false
        detailOpen.value = false
        getList()
      })
    }
  })
}

/** 拒绝审核 */
function handleReject(row) {
  rejectForm.value = {
    id: row.id,
    operatorContact: '',
    remark: ''
  }
  rejectOpen.value = true
}

/** 提交拒绝 */
function submitReject() {
  proxy.$refs["rejectFormRef"].validate(valid => {
    if (valid) {
      rejectJob(rejectForm.value.id, rejectForm.value.operatorContact, rejectForm.value.remark).then(response => {
        proxy.$modal.msgSuccess("审核拒绝成功")
        rejectOpen.value = false
        detailOpen.value = false
        getList()
      })
    }
  })
}

/** 批量通过 */
function handleBatchApprove() {
  const jobIds = ids.value
  proxy.$modal.prompt('请输入您的联系方式', '批量通过审核', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputPattern: /\S+/,
    inputErrorMessage: '联系方式不能为空'
  }).then(({ value }) => {
    return batchApproveJobs(jobIds, value, '批量审核通过')
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("批量审核通过成功")
  }).catch(() => {})
}

/** 批量拒绝 */
function handleBatchReject() {
  const jobIds = ids.value
  // 先输入联系方式
  proxy.$modal.prompt('请输入您的联系方式', '批量拒绝审核 - 步骤1/2', {
    confirmButtonText: '下一步',
    cancelButtonText: '取消',
    inputPattern: /\S+/,
    inputErrorMessage: '联系方式不能为空'
  }).then(({ value: contact }) => {
    // 再输入拒绝原因
    return proxy.$modal.prompt('请输入拒绝原因', '批量拒绝审核 - 步骤2/2', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      inputPattern: /.{5,}/,
      inputErrorMessage: '拒绝原因至少5个字符'
    }).then(({ value: remark }) => {
      return batchRejectJobs(jobIds, contact, remark)
    })
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("批量审核拒绝成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  const exportParams = {
    pageNum: queryParams.value.pageNum,
    pageSize: queryParams.value.pageSize,
    companyName: queryParams.value.companyName,
    title: queryParams.value.title,
    status: queryParams.value.status
  }
  // 将日期范围直接放在顶层参数中
  if (null != daterangeCreatedAt.value && '' != daterangeCreatedAt.value) {
    exportParams.beginCreatedAt = daterangeCreatedAt.value[0]
    exportParams.endCreatedAt = daterangeCreatedAt.value[1]
  }
  proxy.download('recruit/audit/job/export', exportParams, `岗位审核_${new Date().getTime()}.xlsx`, {
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

.audit-log-item {
  padding: 10px 0;
}

.log-header {
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

.log-details {
  margin-top: 10px;
}

.el-timeline {
  padding-left: 20px;
}
</style>
