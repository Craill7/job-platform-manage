<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="投递职位" prop="jobId">
        <el-input
          v-model="queryParams.jobId"
          placeholder="请输入投递职位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投递状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择投递状态" clearable>
          <el-option
            v-for="dict in biz_application_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="投递时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeSubmittedAt"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="状态更新时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeUpdatedAt"
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
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['recruit:applications:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recruit:applications:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recruit:applications:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:applications:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicationsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投递记录ID" align="center" prop="id" />
      <el-table-column label="投递职位" align="center" prop="jobId" />
      <el-table-column label="投递人" align="center" prop="studentUserId" />
      <el-table-column label="投递状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="biz_application_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="投递时间" align="center" prop="submittedAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.submittedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态更新时间" align="center" prop="updatedAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['recruit:applications:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['recruit:applications:remove']">删除</el-button>
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

    <!-- 添加或修改投递记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="applicationsRef" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Applications">
import { listApplications, getApplications, delApplications, addApplications, updateApplications } from "@/api/recruit/applications"

const { proxy } = getCurrentInstance()
const { biz_application_status } = proxy.useDict('biz_application_status')

const applicationsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeSubmittedAt = ref([])
const daterangeUpdatedAt = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    jobId: null,
    status: null,
    submittedAt: null,
    updatedAt: null
  },
  rules: {
    jobId: [
      { required: true, message: "投递职位不能为空", trigger: "blur" }
    ],
    studentUserId: [
      { required: true, message: "投递人不能为空", trigger: "blur" }
    ],
    resumeId: [
      { required: true, message: "简历不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "投递状态不能为空", trigger: "change" }
    ],
    submittedAt: [
      { required: true, message: "投递时间不能为空", trigger: "blur" }
    ],
    updatedAt: [
      { required: true, message: "状态更新时间不能为空", trigger: "blur" }
    ]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询投递记录列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeSubmittedAt && '' != daterangeSubmittedAt) {
    queryParams.value.params["beginSubmittedAt"] = daterangeSubmittedAt.value[0]
    queryParams.value.params["endSubmittedAt"] = daterangeSubmittedAt.value[1]
  }
  if (null != daterangeUpdatedAt && '' != daterangeUpdatedAt) {
    queryParams.value.params["beginUpdatedAt"] = daterangeUpdatedAt.value[0]
    queryParams.value.params["endUpdatedAt"] = daterangeUpdatedAt.value[1]
  }
  listApplications(queryParams.value).then(response => {
    applicationsList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    jobId: null,
    studentUserId: null,
    resumeId: null,
    status: null,
    submittedAt: null,
    updatedAt: null
  }
  proxy.resetForm("applicationsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeSubmittedAt.value = []
  daterangeUpdatedAt.value = []
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加投递记录"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getApplications(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改投递记录"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["applicationsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateApplications(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addApplications(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除投递记录编号为"' + _ids + '"的数据项？').then(function() {
    return delApplications(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('recruit/applications/export', {
    ...queryParams.value
  }, `applications_${new Date().getTime()}.xlsx`)
}

getList()
</script>
