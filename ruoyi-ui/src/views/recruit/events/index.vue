<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="活动标题" prop="eventTitle">
        <el-input
          v-model="queryParams.eventTitle"
          placeholder="请输入活动标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动开始时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeEventStartTime"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      
      <el-form-item label="活动结束时间" style="width: 308px">
        <el-date-picker
          v-model="daterangeEventEndTime"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="创建时间" style="width: 308px">
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
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['recruit:events:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recruit:events:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recruit:events:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:events:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="eventsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="id" />
      <el-table-column label="活动标题" align="center" prop="eventTitle" />
      <el-table-column label="活动开始时间" align="center" prop="eventStartTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eventStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="活动结束时间" align="center" prop="eventEndTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.eventEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="eventLocation" />
      <el-table-column label="面向对象" align="center" prop="targetAudience" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['recruit:events:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['recruit:events:remove']">删除</el-button>
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

    <!-- 添加或修改求职活动管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="eventsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动标题" prop="eventTitle">
          <el-input v-model="form.eventTitle" placeholder="请输入活动标题" />
        </el-form-item>
        <el-form-item label="活动详情">
          <editor v-model="form.eventSummary" :min-height="192"/>
        </el-form-item>
        <el-form-item label="活动开始时间" prop="eventStartTime">
          <el-date-picker clearable
            v-model="form.eventStartTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        
        <el-form-item label="活动结束时间" prop="eventEndTime">
          <el-date-picker clearable
            v-model="form.eventEndTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="eventLocation">
          <el-input v-model="form.eventLocation" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="面向对象" prop="targetAudience">
          <el-input v-model="form.targetAudience" placeholder="请输入面向对象" />
        </el-form-item>
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

<script setup name="Events">
import { listEvents, getEvents, delEvents, addEvents, updateEvents } from "@/api/recruit/events"

const { proxy } = getCurrentInstance()

const eventsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeEventStartTime = ref([])
const daterangeEventEndTime = ref([])
const daterangeCreatedAt = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    eventTitle: null,
    eventStartTime: null,
    eventEndTime: null,
    createdAt: null,
  },
  rules: {
    eventTitle: [
      { required: true, message: "活动标题不能为空", trigger: "blur" }
    ],
    eventStartTime: [
      { required: true, message: "活动开始时间不能为空", trigger: "blur" }
    ],
    createdAt: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updatedAt: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询求职活动管理列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeEventStartTime && '' != daterangeEventStartTime) {
    queryParams.value.params["beginEventStartTime"] = daterangeEventStartTime.value[0]
    queryParams.value.params["endEventStartTime"] = daterangeEventStartTime.value[1]
  }
  if (null != daterangeEventEndTime && '' != daterangeEventEndTime) {
    queryParams.value.params["beginEventEndTime"] = daterangeEventEndTime.value[0]
    queryParams.value.params["endEventEndTime"] = daterangeEventEndTime.value[1]
  }
  if (null != daterangeCreatedAt && '' != daterangeCreatedAt) {
    queryParams.value.params["beginCreatedAt"] = daterangeCreatedAt.value[0]
    queryParams.value.params["endCreatedAt"] = daterangeCreatedAt.value[1]
  }
  listEvents(queryParams.value).then(response => {
    eventsList.value = response.rows
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
    adminUserId: null,
    eventTitle: null,
    eventSummary: null,
    eventStartTime: null,
    eventEndTime: null,
    eventLocation: null,
    targetAudience: null,
    createdAt: null,
    updatedAt: null
  }
  proxy.resetForm("eventsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeEventStartTime.value = []
  daterangeEventEndTime.value = []
  daterangeCreatedAt.value = []
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
  title.value = "添加求职活动管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getEvents(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改求职活动管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["eventsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateEvents(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addEvents(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除求职活动管理编号为"' + _ids + '"的数据项？').then(function() {
    return delEvents(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('recruit/events/export', {
    ...queryParams.value
  }, `events_${new Date().getTime()}.xlsx`)
}

getList()
</script>
