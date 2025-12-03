import request from '@/utils/request'

// 查询企业管理列表
export function listCompanies(query) {
  return request({
    url: '/recruit/companies/list',
    method: 'get',
    params: query
  })
}

// 查询企业管理详细
export function getCompanies(companyId) {
  return request({
    url: '/recruit/companies/' + companyId,
    method: 'get'
  })
}

// 新增企业管理
export function addCompanies(data) {
  return request({
    url: '/recruit/companies',
    method: 'post',
    data: data
  })
}

// 修改企业管理
export function updateCompanies(data) {
  return request({
    url: '/recruit/companies',
    method: 'put',
    data: data
  })
}

// 删除企业管理
export function delCompanies(companyId) {
  return request({
    url: '/recruit/companies/' + companyId,
    method: 'delete'
  })
}
