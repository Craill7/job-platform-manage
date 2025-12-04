<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="fullName">
        <el-input
          v-model="queryParams.fullName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="求职状态" prop="jobSeekingStatus">
        <el-select v-model="queryParams.jobSeekingStatus" placeholder="请选择求职状态" clearable>
          <el-option
            v-for="dict in biz_seek_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['recruit:students:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recruit:students:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recruit:students:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:students:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="头像" align="center" prop="avatarUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.avatarUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="fullName" />
      <el-table-column label="性别" align="center" prop="gender">
        <template #default="scope">
          <dict-tag :options="biz_gender" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="电话" align="center" prop="phoneNumber" />
      <el-table-column label="求职状态" align="center" prop="jobSeekingStatus">
        <template #default="scope">
          <dict-tag :options="biz_seek_status" :value="scope.row.jobSeekingStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="期望职位" align="center" prop="expectedPosition" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['recruit:students:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['recruit:students:remove']">删除</el-button>
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

    <!-- 添加或修改学生信息管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="studentsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="头像" prop="avatarUrl">
          <image-upload v-model="form.avatarUrl"/>
        </el-form-item>
        <el-form-item label="姓名" prop="fullName">
          <el-input v-model="form.fullName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option
              v-for="dict in biz_gender"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="电话" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="生日" prop="dateOfBirth">
          <el-date-picker clearable
            v-model="form.dateOfBirth"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="求职状态" prop="jobSeekingStatus">
          <el-select v-model="form.jobSeekingStatus" placeholder="请选择求职状态">
            <el-option
              v-for="dict in biz_seek_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="期望职位" prop="expectedPosition">
          <el-input v-model="form.expectedPosition" placeholder="请输入期望职位" />
        </el-form-item>
        <el-form-item label="期望最少薪资" prop="expectedMinSalary">
          <el-input v-model="form.expectedMinSalary" placeholder="请输入期望最少薪资" />
        </el-form-item>
        <el-form-item label="期望最多薪资" prop="expectedMaxSalary">
          <el-input v-model="form.expectedMaxSalary" placeholder="请输入期望最多薪资" />
        </el-form-item>
        <el-form-item label="技能掌握" prop="skillsSummary">
          <el-input v-model="form.skillsSummary" type="textarea" placeholder="请输入内容" />
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

<script setup name="Students">
import { listStudents, getStudents, delStudents, addStudents, updateStudents } from "@/api/recruit/students"

const { proxy } = getCurrentInstance()
const { biz_gender, biz_seek_status } = proxy.useDict('biz_gender', 'biz_seek_status')

const studentsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    fullName: null,
    studentId: null,
    jobSeekingStatus: null,
  },
  rules: {
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询学生信息管理列表 */
function getList() {
  loading.value = true
  listStudents(queryParams.value).then(response => {
    studentsList.value = response.rows
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
    userId: null,
    avatarUrl: null,
    fullName: null,
    gender: null,
    studentId: null,
    phoneNumber: null,
    dateOfBirth: null,
    jobSeekingStatus: null,
    expectedPosition: null,
    expectedMinSalary: null,
    expectedMaxSalary: null,
    skillsSummary: null,
    currentTemplateId: null
  }
  proxy.resetForm("studentsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加学生信息管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _userId = row.userId || ids.value
  getStudents(_userId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改学生信息管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["studentsRef"].validate(valid => {
    if (valid) {
      if (form.value.userId != null) {
        updateStudents(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addStudents(form.value).then(response => {
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
  const _userIds = row.userId || ids.value
  proxy.$modal.confirm('是否确认删除学生信息管理编号为"' + _userIds + '"的数据项？').then(function() {
    return delStudents(_userIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('recruit/students/export', {
    ...queryParams.value
  }, `students_${new Date().getTime()}.xlsx`)
}

getList()
</script>
