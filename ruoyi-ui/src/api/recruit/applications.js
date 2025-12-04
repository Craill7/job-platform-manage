import request from '@/utils/request'

// 查询投递记录列表
export function listApplications(query) {
  return request({
    url: '/recruit/applications/list',
    method: 'get',
    params: query
  })
}

// 查询投递记录详细
export function getApplications(id) {
  return request({
    url: '/recruit/applications/' + id,
    method: 'get'
  })
}

// 新增投递记录
export function addApplications(data) {
  return request({
    url: '/recruit/applications',
    method: 'post',
    data: data
  })
}

// 修改投递记录
export function updateApplications(data) {
  return request({
    url: '/recruit/applications',
    method: 'put',
    data: data
  })
}

// 删除投递记录
export function delApplications(id) {
  return request({
    url: '/recruit/applications/' + id,
    method: 'delete'
  })
}
