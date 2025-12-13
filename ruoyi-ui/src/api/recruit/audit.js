import request from '@/utils/request'

// ==================== MOCK 数据存储 ====================
// 模拟数据库存储，实际项目中后端会处理
const mockJobList = [
  {
    id: 1,
    companyId: 1,
    companyName: "阿里巴巴",
    title: "Java高级开发工程师",
    typeName: "技术研发",
    workLocation: "浙江省杭州市西湖区",
    minSalary: 20,
    maxSalary: 40,
    workNature: 2,
    status: 10,
    postedByUserId: 2,
    postedByUserName: "张三",
    createdAt: "2025-12-07 10:30:00",
    updatedAt: "2025-12-07 10:30:00",
    auditedAt: null,
    auditRemark: null
  },
  {
    id: 2,
    companyId: 2,
    companyName: "腾讯科技",
    title: "前端开发工程师",
    typeName: "技术研发",
    workLocation: "广东省深圳市南山区",
    minSalary: 18,
    maxSalary: 35,
    workNature: 2,
    status: 10,
    postedByUserId: 3,
    postedByUserName: "李四",
    createdAt: "2025-12-06 15:20:00",
    updatedAt: "2025-12-06 15:20:00",
    auditedAt: null,
    auditRemark: null
  },
  {
    id: 3,
    companyId: 1,
    companyName: "阿里巴巴",
    title: "产品经理",
    typeName: "产品运营",
    workLocation: "浙江省杭州市西湖区",
    minSalary: 25,
    maxSalary: 45,
    workNature: 2,
    status: 20,
    postedByUserId: 2,
    postedByUserName: "张三",
    createdAt: "2025-12-05 09:00:00",
    updatedAt: "2025-12-06 10:00:00",
    auditedAt: "2025-12-06 10:00:00",
    auditRemark: null
  },
  {
    id: 4,
    companyId: 3,
    companyName: "字节跳动",
    title: "算法工程师",
    typeName: "技术研发",
    workLocation: "北京市海淀区",
    minSalary: 30,
    maxSalary: 50,
    workNature: 2,
    status: 30,
    postedByUserId: 4,
    postedByUserName: "王五",
    createdAt: "2025-12-04 14:00:00",
    updatedAt: "2025-12-05 11:00:00",
    auditedAt: "2025-12-05 11:00:00",
    auditRemark: "岗位描述不够详细，请补充完整后重新提交"
  }
]

const mockCompanyList = [
  {
    userId: 2,
    email: "hr@alibaba.com",
    role: 2,
    status: 0,
    companyName: "阿里巴巴",
    createdAt: "2025-12-05 09:00:00",
    updatedAt: "2025-12-05 09:00:00",
    lastLoginAt: "2025-12-05 09:30:00",
    auditRemark: null
  },
  {
    userId: 3,
    email: "hr@tencent.com",
    role: 2,
    status: 0,
    companyName: "腾讯科技",
    createdAt: "2025-12-06 10:00:00",
    updatedAt: "2025-12-06 10:00:00",
    lastLoginAt: "2025-12-06 10:30:00",
    auditRemark: null
  },
  {
    userId: 4,
    email: "hr@bytedance.com",
    role: 2,
    status: 1,
    companyName: "字节跳动",
    createdAt: "2025-12-03 08:00:00",
    updatedAt: "2025-12-04 09:00:00",
    lastLoginAt: "2025-12-07 14:00:00",
    auditRemark: null
  },
  {
    userId: 5,
    email: "hr@baidu.com",
    role: 2,
    status: 2,
    companyName: "百度公司",
    createdAt: "2025-12-02 15:00:00",
    updatedAt: "2025-12-03 10:00:00",
    lastLoginAt: "2025-12-02 16:00:00",
    auditRemark: "邮箱格式不符合要求，请使用企业邮箱"
  },
  {
    userId: 6,
    email: "hr@meituan.com",
    role: 2,
    status: 0,
    companyName: "美团",
    createdAt: "2025-12-07 15:00:00",
    updatedAt: "2025-12-07 15:00:00",
    lastLoginAt: null,
    auditRemark: null
  },
  {
    userId: 7,
    email: "contact@jd.com",
    role: 2,
    status: 0,
    companyName: "京东",
    createdAt: "2025-12-07 16:30:00",
    updatedAt: "2025-12-07 16:30:00",
    lastLoginAt: null,
    auditRemark: null
  }
]

// 模拟延迟
const mockDelay = (ms = 300) => new Promise(resolve => setTimeout(resolve, ms))

// ==================== 岗位审核相关接口 ====================

/**
 * 查询岗位审核列表
 * @param {Object} query - 查询参数
 * @returns {Promise}
 */
export function listJobAudit(query) {
  return request({
    url: '/recruit/audit/job/list',
    method: 'get',
    params: query
  })
}

/**
 * 查询岗位审核详细信息
 * @param {Number} jobId - 岗位ID
 * @returns {Promise}
 */
export function getJobAudit(jobId) {
  return request({
    url: `/recruit/audit/job/${jobId}`,
    method: 'get'
  })
}

/**
 * 通过岗位审核
 * @param {Number} jobId - 岗位ID
 * @param {String} operatorContact - 审核人联系方式
 * @param {String} remark - 审核备注（可选）
 * @returns {Promise}
 */
export function approveJob(jobId, operatorContact, remark = '') {
  return request({
    url: `/recruit/audit/job/approve/${jobId}`,
    method: 'put',
    data: {
      operatorContact,
      remark
    }
  })
}

/**
 * 拒绝岗位审核
 * @param {Number} jobId - 岗位ID
 * @param {String} operatorContact - 审核人联系方式
 * @param {String} remark - 拒绝原因（必填）
 * @returns {Promise}
 */
export function rejectJob(jobId, operatorContact, remark) {
  return request({
    url: `/recruit/audit/job/reject/${jobId}`,
    method: 'put',
    data: {
      operatorContact,
      remark
    }
  })
}

/**
 * 批量通过岗位审核
 * @param {Array} jobIds - 岗位ID数组
 * @param {String} operatorContact - 审核人联系方式
 * @param {String} remark - 审核备注
 * @returns {Promise}
 */
export function batchApproveJobs(jobIds, operatorContact, remark = '') {
  return request({
    url: '/recruit/audit/job/batch/approve',
    method: 'put',
    data: {
      jobIds,
      operatorContact,
      remark
    }
  })
}

/**
 * 批量拒绝岗位审核
 * @param {Array} jobIds - 岗位ID数组
 * @param {String} operatorContact - 审核人联系方式
 * @param {String} remark - 拒绝原因（必填）
 * @returns {Promise}
 */
export function batchRejectJobs(jobIds, operatorContact, remark) {
  return request({
    url: '/recruit/audit/job/batch/reject',
    method: 'put',
    data: {
      jobIds,
      operatorContact,
      remark
    }
  })
}

/**
 * 查询岗位审核历史记录
 * @param {Number} jobId - 岗位ID
 * @returns {Promise}
 */
export function getJobAuditLogs(jobId) {
  return request({
    url: `/recruit/audit/job/${jobId}/logs`,
    method: 'get'
  })
}

/**
 * 导出岗位审核数据
 * @param {Object} query - 查询参数
 * @returns {Promise}
 */
export function exportJobAudit(query) {
  return request({
    url: '/recruit/audit/job/export',
    method: 'post',
    data: query
  })
}

// ==================== 企业账号审核相关接口 ====================

/**
 * 查询企业账号审核列表
 * @param {Object} query - 查询参数
 * @returns {Promise}
 */
export function listCompanyAudit(query) {
  return request({
    url: '/recruit/audit/company/list',
    method: 'get',
    params: query
  })
}

/**
 * 查询企业账号审核详细信息
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function getCompanyAudit(userId) {
  return request({
    url: `/recruit/audit/company/${userId}`,
    method: 'get'
  })
}

/**
 * 通过企业账号审核
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function approveCompany(userId) {
  return request({
    url: `/recruit/audit/company/approve/${userId}`,
    method: 'put'
  })
}

/**
 * 拒绝企业账号审核
 * @param {Number} userId - 用户ID
 * @returns {Promise}
 */
export function rejectCompany(userId) {
  return request({
    url: `/recruit/audit/company/reject/${userId}`,
    method: 'put'
  })
}

/**
 * 批量通过企业账号审核
 * @param {String} userIds - 多个用户ID（逗号分隔的字符串）
 * @returns {Promise}
 */
export function batchApproveCompanies(userIds) {
  return request({
    url: '/recruit/audit/company/batch/approve',
    method: 'put',
    data: {
      userIds
    }
  })
}

/**
 * 批量拒绝企业账号审核
 * @param {String} userIds - 多个用户ID（逗号分隔的字符串）
 * @returns {Promise}
 */
export function batchRejectCompanies(userIds) {
  return request({
    url: '/recruit/audit/company/batch/reject',
    method: 'put',
    data: {
      userIds
    }
  })
}

/**
 * 导出企业账号审核数据
 * @param {Object} query - 查询参数
 * @returns {Promise}
 */
export function exportCompanyAudit(query) {
  return request({
    url: '/recruit/audit/company/export',
    method: 'post',
    data: query
  })
}
