import request from '@/utils/request'

// 查询岗位管理列表
export function listJobs(query) {
  return request({
    url: '/recruit/jobs/list',
    method: 'get',
    params: query
  })
}

// 查询岗位管理详细
export function getJobs(id) {
  return request({
    url: '/recruit/jobs/' + id,
    method: 'get'
  })
}

// 新增岗位管理
export function addJobs(data) {
  return request({
    url: '/recruit/jobs',
    method: 'post',
    data: data
  })
}

// 修改岗位管理
export function updateJobs(data) {
  return request({
    url: '/recruit/jobs',
    method: 'put',
    data: data
  })
}

// 删除岗位管理
export function delJobs(id) {
  return request({
    url: '/recruit/jobs/' + id,
    method: 'delete'
  })
}

// 根据职位名称模糊查询职位列表（用于下拉选择）
export function searchJobs(title) {
  return request({
    url: '/recruit/jobs/search',
    method: 'get',
    params: { title: title }
  })
}