import request from '@/utils/request'

// 查询规模信息管理列表
export function listScales(query) {
  return request({
    url: '/recruit/scales/list',
    method: 'get',
    params: query
  })
}

// 查询规模信息管理详细
export function getScales(id) {
  return request({
    url: '/recruit/scales/' + id,
    method: 'get'
  })
}

// 新增规模信息管理
export function addScales(data) {
  return request({
    url: '/recruit/scales',
    method: 'post',
    data: data
  })
}

// 修改规模信息管理
export function updateScales(data) {
  return request({
    url: '/recruit/scales',
    method: 'put',
    data: data
  })
}

// 删除规模信息管理
export function delScales(id) {
  return request({
    url: '/recruit/scales/' + id,
    method: 'delete'
  })
}
