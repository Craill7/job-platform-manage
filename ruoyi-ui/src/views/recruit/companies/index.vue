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
          v-hasPermi="['recruit:companies:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recruit:companies:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recruit:companies:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['recruit:companies:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companiesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="公司ID" align="center" prop="companyId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="公司Logo" align="center" prop="logoUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.logoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="行业领域" align="center" prop="industryName">
        <template #default="scope">
          <span>{{ scope.row.industryName || '-' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="企业性质" align="center" prop="natureName">
        <template #default="scope">
          <span>{{ scope.row.natureName || '-' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="公司规模" align="center" prop="companyScaleName">
        <template #default="scope">
          <span>{{ scope.row.companyScaleName || '-' }}</span>
        </template>
      </el-table-column>

      <el-table-column label="联系人姓名" align="center" prop="contactPersonName" />
      <el-table-column label="联系人电话" align="center" prop="contactPersonPhone" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['recruit:companies:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['recruit:companies:remove']">删除</el-button>
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

    <!-- 添加或修改企业管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="companiesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入关联用户ID" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司介绍" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="公司Logo" prop="logoUrl">
          <image-upload v-model="form.logoUrl" action="/recruit/companies/logo"/>
        </el-form-item>
        <el-form-item label="行业领域" prop="industryId">
          <el-select v-model="form.industryId" placeholder="请选择行业领域" clearable>
            <el-option
              v-for="item in industriesOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="企业性质" prop="natureId">
          <el-select v-model="form.natureId" placeholder="请选择企业性质" clearable>
            <el-option
              v-for="item in naturesOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司规模" prop="companyScaleId">
          <el-select v-model="form.companyScaleId" placeholder="请选择公司规模" clearable>
            <el-option
              v-for="item in scalesOptions"
              :key="item.id"
              :label="item.scale"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司地址" prop="companyAddress">
          <el-input v-model="form.companyAddress" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="联系人姓名" prop="contactPersonName">
          <el-input v-model="form.contactPersonName" placeholder="请输入联系人姓名" />
        </el-form-item>
        <el-form-item label="联系人电话" prop="contactPersonPhone">
          <el-input v-model="form.contactPersonPhone" placeholder="请输入联系人电话" />
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

<script setup name="Companies">
import { listCompanies, getCompanies, delCompanies, addCompanies, updateCompanies } from "@/api/recruit/companies"
import { listIndustries } from "@/api/recruit/industries"
import { listNatures } from "@/api/recruit/natures"
import { listScales } from "@/api/recruit/scales"

const { proxy } = getCurrentInstance()

const companiesList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const daterangeCreatedAt = ref([])
const industriesOptions = ref([])
const naturesOptions = ref([])
const scalesOptions = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    companyName: null,
    industryId: null,
    natureId: null,
    companyScaleId: null,
    createdAt: null
  },
  rules: {
    userId: [
      { required: true, message: "关联用户ID不能为空", trigger: "blur" }
    ],
    companyName: [
      { required: true, message: "公司名称不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "公司介绍不能为空", trigger: "blur" }
    ],
    logoUrl: [
      { required: true, message: "公司Logo不能为空", trigger: "blur" }
    ],
    industryId: [
      { required: true, message: "行业领域不能为空", trigger: "change" }
    ],
    natureId: [
      { required: true, message: "企业性质不能为空", trigger: "change" }
    ],
    companyScaleId: [
      { required: true, message: "公司规模不能为空", trigger: "change" }
    ],
    companyAddress: [
      { required: true, message: "公司地址不能为空", trigger: "blur" }
    ],
    contactPersonName: [
      { required: true, message: "联系人姓名不能为空", trigger: "blur" }
    ],
    contactPersonPhone: [
      { required: true, message: "联系人电话不能为空", trigger: "blur" }
    ]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询企业管理列表 */
function getList() {
  loading.value = true
  queryParams.value.params = {}
  if (null != daterangeCreatedAt && '' != daterangeCreatedAt) {
    queryParams.value.params["beginCreatedAt"] = daterangeCreatedAt.value[0]
    queryParams.value.params["endCreatedAt"] = daterangeCreatedAt.value[1]
  }
  listCompanies(queryParams.value).then(response => {
    companiesList.value = response.rows
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
    companyId: null,
    userId: null,
    companyName: null,
    description: null,
    logoUrl: null,
    industryId: null,
    natureId: null,
    companyScaleId: null,
    companyAddress: null,
    contactPersonName: null,
    contactPersonPhone: null,
    createdAt: null
  }
  proxy.resetForm("companiesRef")
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
  ids.value = selection.map(item => item.companyId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加企业管理"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _companyId = row.companyId || ids.value
  getCompanies(_companyId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改企业管理"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["companiesRef"].validate(valid => {
    if (valid) {
      if (form.value.companyId != null) {
        updateCompanies(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCompanies(form.value).then(response => {
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
  const _companyIds = row.companyId || ids.value
  proxy.$modal.confirm('是否确认删除企业管理编号为"' + _companyIds + '"的数据项？').then(function() {
    return delCompanies(_companyIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('recruit/companies/export', {
    ...queryParams.value
  }, `companies_${new Date().getTime()}.xlsx`)
}

/** 加载行业领域选项 */
function loadIndustries() {
  listIndustries({ pageNum: 1, pageSize: 1000 }).then(response => {
    industriesOptions.value = response.rows || []
  })
}

/** 加载企业性质选项 */
function loadNatures() {
  listNatures({ pageNum: 1, pageSize: 1000 }).then(response => {
    naturesOptions.value = response.rows || []
  })
}

/** 加载公司规模选项 */
function loadScales() {
  listScales({ pageNum: 1, pageSize: 1000 }).then(response => {
    scalesOptions.value = response.rows || []
  })
}

getList()
loadIndustries()
loadNatures()
loadScales()
</script>
