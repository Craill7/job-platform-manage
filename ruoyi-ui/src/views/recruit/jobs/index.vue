<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
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
      <el-form-item label="岗位性质" prop="workNature">
        <el-select v-model="queryParams.workNature" placeholder="请选择岗位性质" clearable  filterable>
          <el-option
            v-for="dict in biz_work_nature"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学历要求" prop="requiredDegree">
        <el-select v-model="queryParams.requiredDegree" placeholder="请选择学历要求" clearable filterable>
          <el-option
            v-for="dict in biz_required_degree_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
      <el-form-item label="岗位状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择岗位状态" clearable filterable>
          <el-option
            v-for="dict in biz_job_status"
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
          v-hasPermi="['recruit:jobs:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recruit:jobs:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recruit:jobs:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:jobs:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jobsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="岗位ID" align="center" prop="id" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="所属部门" align="center" prop="department" />
      <el-table-column label="岗位名称" align="center" prop="title" />
      <el-table-column label="岗位类别" align="center" prop="type">
        <template #default="scope">
          {{ getCategoryName(scope.row.type) }}
        </template>
      </el-table-column>
      <el-table-column label="最少薪资(k/元)" align="center" prop="maxSalary" />
      <el-table-column label="最少薪资(k/元)" align="center" prop="maxSalary" />
      <el-table-column label="岗位性质" align="center" prop="workNature">
        <template #default="scope">
          <dict-tag :options="biz_work_nature" :value="scope.row.workNature"/>
        </template>
      </el-table-column>
      <el-table-column label="学历要求" align="center" prop="requiredDegree">
        <template #default="scope">
          <dict-tag :options="biz_required_degree_level" :value="scope.row.requiredDegree"/>
        </template>
      </el-table-column>
      <el-table-column label="招聘人数" align="center" prop="headcount" />
      <el-table-column label="岗位状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="biz_job_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['recruit:jobs:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['recruit:jobs:remove']">删除</el-button>
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

    <!-- 添加或修改岗位管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="jobsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="companyName">
          <el-autocomplete
            v-model="form.companyName"
            :fetch-suggestions="queryCompanySearch"
            placeholder="请输入公司名称进行搜索"
            clearable
            style="width: 100%"
            @select="handleCompanySelect"
          >
            <template #default="{ item }">
              <div>{{ item.companyName }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="所属部门" prop="department">
          <el-input v-model="form.department" placeholder="请输入所属部门" />
        </el-form-item>
        <el-form-item label="岗位名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入岗位名称" />
        </el-form-item>
        <el-form-item label="岗位类别" prop="type">
          <el-select v-model="form.type" placeholder="请选择岗位类别" clearable>
            <el-option
              v-for="category in jobCategories"
              :key="category.id"
              :label="category.name"
              :value="category.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="岗位描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="岗位要求" prop="techRequirements">
          <el-input v-model="form.techRequirements" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="岗位加分项" prop="bonusPoints">
          <el-input v-model="form.bonusPoints" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="最少薪资" prop="minSalary">
          <el-input v-model="form.minSalary" placeholder="请输入最少薪资(校招k/实习￥)" />
        </el-form-item>
        <el-form-item label="最多薪资" prop="maxSalary">
          <el-input v-model="form.maxSalary" placeholder="请输入最少薪资(校招k/实习￥)" />
        </el-form-item>
        <el-form-item label="省份" prop="provinceId">
          <el-select v-model="form.provinceId" placeholder="请选择省份" clearable @change="handleProvinceChange">
            <el-option
              v-for="province in provinces"
              :key="province.id"
              :label="province.name"
              :value="province.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="城市" prop="cityId">
          <el-select v-model="form.cityId" placeholder="请先选择省份" clearable :disabled="!form.provinceId">
            <el-option
              v-for="city in cities"
              :key="city.id"
              :label="city.name"
              :value="city.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="详细地址" prop="addressDetail">
          <el-input v-model="form.addressDetail" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="岗位性质" prop="workNature">
          <el-select v-model="form.workNature" placeholder="请选择岗位性质">
            <el-option
              v-for="dict in biz_work_nature"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学历要求" prop="requiredDegree">
          <el-select v-model="form.requiredDegree" placeholder="请选择学历要求">
            <el-option
              v-for="dict in biz_required_degree_level"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="招聘人数" prop="headcount">
          <el-input v-model="form.headcount" placeholder="请输入招聘人数" />
        </el-form-item>
        <el-form-item label="要求入职时间" prop="requiredStartDate">
          <el-date-picker clearable
            v-model="form.requiredStartDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择要求入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="招聘截止日期" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择招聘截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="岗位状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择岗位状态">
            <el-option
              v-for="dict in biz_job_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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

<script setup name="Jobs">
import { listJobs, getJobs, delJobs, addJobs, updateJobs } from "@/api/recruit/jobs"
import { listCategories } from "@/api/recruit/categories"
import { listProvinces } from "@/api/recruit/provinces"
import { listCitiesByProvince } from "@/api/recruit/cities"
import { searchCompanies } from "@/api/recruit/companies"

const { proxy } = getCurrentInstance()
const { biz_work_nature, biz_job_status, biz_required_degree_level } = proxy.useDict('biz_work_nature', 'biz_job_status', 'biz_required_degree_level')

const jobsList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeRequiredStartDate = ref([])
const daterangeDeadline = ref([])
const daterangeCreatedAt = ref([])
const daterangeUpdatedAt = ref([])
const jobCategories = ref([])
const provinces = ref([])
const cities = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    companyName: null,
    title: null,
    type: null,
    workNature: null,
    requiredDegree: null,
    createdAt: null,
    status: null,
  },
  rules: {
    companyName: [
      { required: true, message: "公司名称不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询岗位管理列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeRequiredStartDate && '' != daterangeRequiredStartDate) {
    queryParams.value.params["beginRequiredStartDate"] = daterangeRequiredStartDate.value[0]
    queryParams.value.params["endRequiredStartDate"] = daterangeRequiredStartDate.value[1]
  }
  if (null != daterangeDeadline && '' != daterangeDeadline) {
    queryParams.value.params["beginDeadline"] = daterangeDeadline.value[0]
    queryParams.value.params["endDeadline"] = daterangeDeadline.value[1]
  }
  if (null != daterangeCreatedAt && '' != daterangeCreatedAt) {
    queryParams.value.params["beginCreatedAt"] = daterangeCreatedAt.value[0]
    queryParams.value.params["endCreatedAt"] = daterangeCreatedAt.value[1]
  }
  if (null != daterangeUpdatedAt && '' != daterangeUpdatedAt) {
    queryParams.value.params["beginUpdatedAt"] = daterangeUpdatedAt.value[0]
    queryParams.value.params["endUpdatedAt"] = daterangeUpdatedAt.value[1]
  }
  listJobs(queryParams.value).then(response => {
    jobsList.value = response.rows
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
    companyName: null,
    department: null,
    title: null,
    type: null,
    description: null,
    techRequirements: null,
    bonusPoints: null,
    minSalary: null,
    maxSalary: null,
    provinceId: null,
    cityId: null,
    addressDetail: null,
    workNature: null,
    requiredDegree: null,
    headcount: null,
    requiredStartDate: null,
    deadline: null,
    createdAt: null,
    updatedAt: null,
    status: null,
    postedByUserId: null
  }
  proxy.resetForm("jobsRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  daterangeRequiredStartDate.value = []
  daterangeDeadline.value = []
  daterangeCreatedAt.value = []
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
  title.value = "添加岗位管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getJobs(_id).then(response => {
    form.value = response.data
    // 如果已有省份ID，加载对应的城市列表
    if (form.value.provinceId) {
      handleProvinceChange(form.value.provinceId)
    }
    open.value = true
    title.value = "修改岗位管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["jobsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateJobs(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addJobs(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除岗位管理编号为"' + _ids + '"的数据项？').then(function() {
    return delJobs(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('recruit/jobs/export', {
    ...queryParams.value
  }, `jobs_${new Date().getTime()}.xlsx`)
}

// 加载岗位类别列表
function loadJobCategories() {
  listCategories({}).then(response => {
    jobCategories.value = response.rows || []
  })
}

// 根据岗位类别ID获取名称
function getCategoryName(categoryId) {
  if (!categoryId) {
    return ''
  }
  const category = jobCategories.value.find(item => item.id === categoryId)
  return category ? category.name : ''
}

// 加载省份列表
function loadProvinces() {
  listProvinces({}).then(response => {
    provinces.value = response.data || []
  })
}

// 省份改变时加载城市列表
function handleProvinceChange(provinceId) {
  form.value.cityId = null
  cities.value = []
  if (provinceId) {
    listCitiesByProvince(provinceId).then(response => {
      cities.value = response.data || []
    })
  }
}

// 公司名称搜索
function queryCompanySearch(queryString, cb) {
  if (!queryString || queryString.trim() === '') {
    cb([])
    return
  }
  searchCompanies(queryString).then(response => {
    const companies = response.data || []
    const results = companies.map(company => ({
      value: company.companyName,
      companyName: company.companyName,
      companyId: company.companyId
    }))
    cb(results)
  }).catch(() => {
    cb([])
  })
}

// 选择公司
function handleCompanySelect(item) {
  form.value.companyName = item.companyName
}

getList()
loadJobCategories()
loadProvinces()
</script>
